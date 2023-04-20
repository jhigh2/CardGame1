package gui;

import game.Board;
import game.Computer;
import game.Player;
import game.card.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

@SuppressWarnings("serial")
public class GuiBoard extends JFrame {

    public static final Board board = new Board();
    public final static Player player = board.getPlayer();
    public final static Computer computer = board.getComputer();
    public static boolean hasDrawn = false;
    private int wins = 0;
    private int losses = 0;
    private JPanel contentPane;
    private HandPanel panel_hand;
    private JLabel lblTurnCount;
    public static  JLabel lblPlayerHealth = new JLabel();
    public static JLabel lblOpponentHealth = new JLabel();
    public static JLabel lblMana;

    /**
     * Create the frame.
     */
    public GuiBoard() {
        readWinsAndLosses();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 1000, 750);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 50, 15, 15));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = create_guiBoardLayout();
        contentPane.setLayout(gbl_contentPane);
        lblTurnCount = create_turnCount();
        GridBagConstraints gbc_lblTurnCount = create_TurnCountConstraints();
        contentPane.add(lblTurnCount, gbc_lblTurnCount);

        JLabel lblOpponentHealth = create_opponentHealthLbl();
        GridBagConstraints gbc_lblOpponentHealth = create_opponentHealthLblConstraints();
        contentPane.add(lblOpponentHealth, gbc_lblOpponentHealth);


        JLabel lblPlayerHealth = create_playerHealthLbl();
        GridBagConstraints gbc_lblPlayerHealth = create_playerHeathLblConstraints();
        contentPane.add(lblPlayerHealth, gbc_lblPlayerHealth);

        BattleAreaPanel panel_battleArea = create_battleAreaPanel();
        GridBagConstraints gbc_panel_BattleArea = create_battleAreaPanelConstraints();
        contentPane.add(panel_battleArea, gbc_panel_BattleArea);
        JButton btnEndTurn = create_endTurnBtn();
        GridBagConstraints gbc_btnEndTurn = create_endTurnBtnConstraints();
        contentPane.add(btnEndTurn, gbc_btnEndTurn);
        panel_hand = create_handPanel();
        GridBagConstraints gbc_panel_Hand = create_handPanelConstraints();
        contentPane.add(panel_hand, gbc_panel_Hand);
        JButton btnDraw = create_drawBtn();
        GridBagConstraints gbc_btnDraw = create_drawBtnConstraints();
        contentPane.add(btnDraw, gbc_btnDraw);
        lblMana = create_manaCount();
        GridBagConstraints gbc_lblMana = create_manaCountConstraints();
        contentPane.add(lblMana, gbc_lblMana);
        JLabel lblFileIOWins = getLblFileIOWins(String.valueOf(wins));
        GridBagConstraints gbc_lblFileIOWins = newLblFileIOWinsConstraints();
        contentPane.add(lblFileIOWins, gbc_lblFileIOWins);
        JLabel lblFileIOLoses = newLblFileIoLosses(String.valueOf(losses));
        GridBagConstraints gbc_lblFileIOLoses = newLblFileIOLossesConstraints();
        contentPane.add(lblFileIOLoses, gbc_lblFileIOLoses);
    }

    private static JLabel newLblFileIoLosses(String losses) {
        return new JLabel("Losses: " + losses + "\r\n");
    }

    private static GridBagConstraints newLblFileIOLossesConstraints() {
        GridBagConstraints gbc_lblFileIOLoses = new GridBagConstraints();
        gbc_lblFileIOLoses.insets = new Insets(0, 0, 5, 5);
        gbc_lblFileIOLoses.gridx = 0;
        gbc_lblFileIOLoses.gridy = 5;
        return gbc_lblFileIOLoses;
    }

    private static GridBagConstraints newLblFileIOWinsConstraints() {
        GridBagConstraints gbc_lblFileIOWins = new GridBagConstraints();
        gbc_lblFileIOWins.insets = new Insets(0, 0, 5, 5);
        gbc_lblFileIOWins.gridx = 0;
        gbc_lblFileIOWins.gridy = 4;
        return gbc_lblFileIOWins;
    }

    private static JLabel getLblFileIOWins(String wins) {
        return new JLabel("Wins: " + wins);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiBoard frame = new GuiBoard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JLabel create_turnCount() {
        JLabel lblTurnCount = new JLabel("Turn Count: " + player.getNumberOfTurns());
        lblTurnCount.setHorizontalAlignment(SwingConstants.LEFT);
        return lblTurnCount;
    }

    private GridBagConstraints create_TurnCountConstraints() {
        GridBagConstraints gbc_lblTurnCount = new GridBagConstraints();
        gbc_lblTurnCount.insets = new Insets(0, 0, 5, 5);
        gbc_lblTurnCount.gridx = 1;
        gbc_lblTurnCount.gridy = 0;
        return gbc_lblTurnCount;
    }

    private JLabel create_manaCount() {
        JLabel lblMana = new JLabel("Mana: " + player.getMana());
        return lblMana;
    }

    private GridBagConstraints create_manaCountConstraints() {
        GridBagConstraints gbc_lblMana = new GridBagConstraints();
        gbc_lblMana.insets = new Insets(0, 0, 0, 5);
        gbc_lblMana.gridx = 1;
        gbc_lblMana.gridy = 3;
        return gbc_lblMana;
    }

    /**
     * Sets the layout of the board
     *
     * @return
     */
    private GridBagLayout create_guiBoardLayout() {
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        return gbl_contentPane;
    }

    /**
     * Creates the "Draw" button
     *
     * @return
     */
    private JButton create_drawBtn() {
        JButton btnDraw = new JButton("Draw\r\n");
        btnDraw.addActionListener(new ActionListener() {
            /**
             * pulls a card from the deck to the players hand. (see HandPanel class)
             * The user can only draw one card per turn
             */
            public void actionPerformed(ActionEvent e) {
                if (hasDrawn == false) {
                    panel_hand.addCardToHand();
                    hasDrawn = true;
                }
            }
        });
        return btnDraw;
    }

    /**
     * sets the layout constraints of the "Draw" button.
     *
     * @return
     */
    private GridBagConstraints create_drawBtnConstraints() {
        GridBagConstraints gbc_btnDraw = new GridBagConstraints();
        gbc_btnDraw.insets = new Insets(0, 0, 5, 0);
        gbc_btnDraw.gridx = 2;
        gbc_btnDraw.gridy = 2;
        return gbc_btnDraw;
    }

    /**
     * creates the "End Turn" button
     *
     * @return
     */
    private JButton create_endTurnBtn() {
        JButton btnEndTurn = new JButton("End Turn\r\n");
        btnEndTurn.addActionListener(new ActionListener() {

            /**
             * Ends the turn and resets the ability to draw and place a card
             */
            public void actionPerformed(ActionEvent e) {
                hasDrawn = false;
                HandPanel.hasPlayed = false;
                BattleAreaPanel.battleAreaButtons.forEach(x -> x.setSelected(false));
                player.setNumberOfTurns(player.getNumberOfTurns() + 1);
                player.setMana(player.getMana() + player.getNumberOfTurns());
                lblTurnCount.setText("Turn Count: " + player.getNumberOfTurns());
                lblMana.setText("Mana: " + player.getMana());

                //Attack all enemy cards and update the board
                board.attackAllEnemies(player);
                updateBoard();

                board.doComputerMove();
                //This method updates the computers mana internally. Computer draws a card, places it, then attacks all enemy cards.
                updateBoard();
            }
        });
        return btnEndTurn;
    }

    /**
     * Updates all components on the board.
     */
    private void updateBoard() {
        //Firstly check if the game is over and write to wins and losses if it is.
        if (player.getHealth() <= 0 || computer.getHealth() <= 0){
            writeWinsAndLosses(player.getHealth() > 0);
            System.out.print("Victory: " + Boolean.toString((player.getHealth()>0)));

            System.exit(0);
        }

        //Update the board method
        for (int i = 0; i < 8; i++){
            Card card = board.getCardAtLocation(i);
            if (card != null) {
                if (i < 4) {
                    JToggleButton playerButton = (JToggleButton) BattleAreaPanel.cardMap.get(i);
                    playerButton.setText(card.toString());
                } else {
                    JLabel computerLabel = (JLabel) BattleAreaPanel.cardMap.get(i);
                    computerLabel.setText(card.toString());
                }
            } else {
                if (i < 4) {
                    JToggleButton playerButton = (JToggleButton) BattleAreaPanel.cardMap.get(i);
                    playerButton.setText("Empty Slot");
                    HandPanel.listBattleAreaSlotAvailable.set(i, true);
                } else {
                    JLabel computerLabel = (JLabel) BattleAreaPanel.cardMap.get(i);
                    computerLabel.setText("Empty Slot");
                }
            }
        }
        lblOpponentHealth.setText("Computer Health: " + computer.getHealth());
        lblPlayerHealth.setText("Player Health: " + player.getHealth());
    }

    /**
     * sets the layout constraints of the "End Turn" button.
     *
     * @return
     */
    private GridBagConstraints create_endTurnBtnConstraints() {
        GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
        gbc_btnEndTurn.insets = new Insets(0, 0, 5, 0);
        gbc_btnEndTurn.gridx = 2;
        gbc_btnEndTurn.gridy = 1;
        return gbc_btnEndTurn;
    }

    private JLabel create_opponentHealthLbl() {
        lblOpponentHealth = new JLabel("Computer Health: " + computer.getHealth() + "\r\n");
        lblOpponentHealth.setFont(new Font("Tahoma", Font.BOLD, 21));
        return lblOpponentHealth;
    }

    /**
     * Sets the layout location of the opponents health counter
     *
     * @return
     */
    private GridBagConstraints create_opponentHealthLblConstraints() {
        GridBagConstraints gbc_lblOpponentHealth = new GridBagConstraints();
        gbc_lblOpponentHealth.insets = new Insets(0, 0, 5, 0);
        gbc_lblOpponentHealth.gridx = 3;
        gbc_lblOpponentHealth.gridy = 0;
        return gbc_lblOpponentHealth;
    }

    /**
     * Creates the players health counter
     *
     * @return
     */
    private JLabel create_playerHealthLbl() {
        lblPlayerHealth = new JLabel("Player Health: " + player.getHealth() + "\r\n");
        lblPlayerHealth.setFont(new Font("Tahoma", Font.BOLD, 21));
        return lblPlayerHealth;
    }

    /**
     * Sets the layout location of the players health counter
     *
     * @return
     */
    private GridBagConstraints create_playerHeathLblConstraints() {
        GridBagConstraints gbc_lblPlayerHealth = new GridBagConstraints();
        gbc_lblPlayerHealth.gridx = 3;
        gbc_lblPlayerHealth.gridy = 5;
        return gbc_lblPlayerHealth;
    }

    /**
     * Creates the panel that contains the players hand (See HandPanel class)
     *
     * @return
     */
    private HandPanel create_handPanel() {
        HandPanel panel_Hand = new HandPanel();
        return panel_Hand;
    }

    /**
     * Sets the layout of the handPanel
     *
     * @return
     */
    private GridBagConstraints create_handPanelConstraints() {
        GridBagConstraints gbc_panel_Hand = new GridBagConstraints();
        gbc_panel_Hand.insets = new Insets(0, 0, 5, 5);
        gbc_panel_Hand.fill = GridBagConstraints.BOTH;
        gbc_panel_Hand.gridx = 1;
        gbc_panel_Hand.gridy = 2;
        return gbc_panel_Hand;
    }

    /**
     * Creates the panel that contains the Battle Area (See BattleAreaPanel class)
     *
     * @return
     */
    private BattleAreaPanel create_battleAreaPanel() {
        BattleAreaPanel panel_BattleArea = new BattleAreaPanel();
        return panel_BattleArea;
    }

    /**
     * Sets the layout of the BattleAreaPanel
     *
     * @return
     */
    private GridBagConstraints create_battleAreaPanelConstraints() {
        GridBagConstraints gbc_panel_BattleArea = new GridBagConstraints();
        gbc_panel_BattleArea.insets = new Insets(0, 0, 5, 5);
        gbc_panel_BattleArea.fill = GridBagConstraints.BOTH;
        gbc_panel_BattleArea.gridx = 1;
        gbc_panel_BattleArea.gridy = 1;
        return gbc_panel_BattleArea;
    }

    /**
     * Writes to a file whether the player won or lost the game.
     * The file only contains one line as following:
     * Wins: {wins}, Losses: {losses};
     *
     * @param gameWon
     */
    private void writeWinsAndLosses(Boolean gameWon) {
        String filePath = "src/gui/Resources/WinsAndLosses.txt";
        try (PrintWriter writer = new PrintWriter(new File(filePath))){
            //update games and losses according to if game is won or not
            if (gameWon)
                writer.write("Wins: " + (wins + 1) + ", " + "Losses: " + losses);
            else
                writer.write("Wins: " + wins + ", " + "Losses: " + (losses + 1));
        } catch (FileNotFoundException e) {
            System.out.println("Error: could not find " + filePath);
        }
    }

    /**
     * Reads from a file whether the player won or lost the game.
     * The file only contains one line as following:
     * Wins: {wins}, Losses: {losses};
     */
    private void readWinsAndLosses() {
        String filePath = "src/gui/Resources/WinsAndLosses.txt";
        String winsAndLosses = "";
        try (Scanner reader = new Scanner(new File(filePath))){
            while(reader.hasNextLine())
                winsAndLosses = reader.nextLine();
            String[] strings = winsAndLosses.split(":");
            wins = Integer.parseInt(strings[1].substring(1, strings[1].indexOf(",")));
            losses = Integer.parseInt(strings[2].substring(1));
        } catch (FileNotFoundException e) {
            System.out.println("Error: could not find " + filePath);
        }
    }
}
