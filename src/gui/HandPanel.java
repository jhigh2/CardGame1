package gui;

import game.Board;
import game.card.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing the player's hand.
 * @author Joel High
 * @author John Freeman
 */
@SuppressWarnings("serial")
public class HandPanel extends JPanel {
    public static List<Boolean> listBattleAreaSlotAvailable = new ArrayList<>(Arrays.asList(true, true, true, true));
    public static boolean hasPlayed = false; // Once the player places a card, they can no longer place a card until
    public static boolean placementChosen;
    public int currentSlotInHand = 0; // the package knows how many cards are in the players hands
	// the next turn
    public int cardCounter = 0;
    HashMap<Integer, JComponent> map = new HashMap<>();
    // Check if there is a card already on the board for that index
    Board board = GuiBoard.board;
    private List<JButton> listHand = new ArrayList<>();
    private int totalSlotsAvailable = 7; // only needed if we want to restrict the number of cards a player can hold

    /**
     * Creates the panel. (Constructor)
     */
    public HandPanel() {
        setBorder(new EmptyBorder(100, 0, 0, 0));
        GridBagLayout gridBagLayout = create_handLayout();
        setLayout(gridBagLayout);
    }

    /**
     * Sets the layout of the Hand JPanel
     *
     * @return
     */
    private GridBagLayout create_handLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        return gridBagLayout;
    }

    /**
     * Creates a new card and adds the card to the Hand List
     *
     * @return
     */
    public JButton create_Card() {
        JButton btnNewButton = new JButton();
        Card card = board.getPlayer().drawCard();
        placementChosen = false;
        btnNewButton.setText(card.toString() + "<br>Mana Cost: " + card.getMana() + "</html>");
        btnNewButton.setPreferredSize(new Dimension(150, 150)); // set's the button to be a reasonable size
        cardCounter++;
        listHand.add(btnNewButton); // Adds a new JButton (Card) to the Hand List
        add(btnNewButton); // Add that same new Jbutton (Card) to the JPanel
        btnNewButton.addActionListener(new ActionListener() { // Once the card is chosen
            public void actionPerformed(ActionEvent e) {
                if (board.getPlayer().getMana() >= card.getMana()) {
                    handToBattleArea(0); // Play the card to the Battle Area index 0
                    handToBattleArea(1);
                    handToBattleArea(2);
                    handToBattleArea(3);
                    if (placementChosen == true) { // the placement on the board has to be chosen if the card is
						// going to disappear
                        btnNewButton.setVisible(false);
                        ; // Hides the card so it no longer appears in players hand
                        listHand.remove(btnNewButton);
                        GuiBoard.lblMana.setText("Mana: " + board.getPlayer().getMana());
                    }
                }
            }

            /**
             * @param areaIndex
             */
            private void handToBattleArea(int areaIndex) {
                if (hasPlayed == false) {
                    if (BattleAreaPanel.battleAreaButtons.get(areaIndex).isSelected() && listBattleAreaSlotAvailable.get(areaIndex) == true) {
                        deselectAllBattleAreaButtons(areaIndex);
                        BattleAreaPanel.battleAreaButtons.get(areaIndex).setText(card.toString());
                        BattleAreaPanel.battleAreaButtons.get(areaIndex).setSelected(false);
                        listBattleAreaSlotAvailable.set(areaIndex, false);
                        //GuiBoard.player.setMana(GuiBoard.player.getMana()-card.getMana());
                        placementChosen = true;
                        board.setCardAtLocation(card, areaIndex);
                    }
                }
            }
        });
        return btnNewButton;
    }

    /**
     * Deselects the other JToggle buttons
     * (Removes a bug we encountered where the player could place multiple cards at once and have negative mana)
     *
     * @param areaIndex the selected index
     */
    private void deselectAllBattleAreaButtons(int areaIndex) {
        for (int i = 0; i < 4; i++) {
            if (BattleAreaPanel.battleAreaButtons.get(i) != BattleAreaPanel.battleAreaButtons.get(areaIndex))
                BattleAreaPanel.battleAreaButtons.get(i).setSelected(false);
        }
    }

    /**
	 * The Gui has a button "Deck". When "Deck is pressed a card is added with (addcard()) to the players hand. The
	 * card is created with an Icon and the class counts the card
	 * soas to know how many cards are in the players hand.
	 */
    public void addCardToHand() {
        //listHandIcon.add(new ImageIcon(HandPanel.class.getResource(files[index]))); // adds new image to list Icon
        create_Card(); // Creates a new card
        revalidate();
        //listHand.get(currentSlotInHand).setIcon(listHandIcon.get(currentSlotInHand)); // sets the icon to the new card
        currentSlotInHand = ++currentSlotInHand % totalSlotsAvailable; // allows the program to keep trying
		// regardless of how small our minimum slots allowed value is.
        //index = ++index % files.length; // allows the program to keep trying regardless if the deck is out of cards.
    }
}