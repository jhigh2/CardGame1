package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattleAreaPanel extends JPanel {

    public static List<JToggleButton> battleAreaButtons = new ArrayList<>();
    public static List<JLabel> computerInPlayList = new ArrayList<>();
    public static HashMap<Integer, JComponent> cardMap = new HashMap<>(8);

    /**
     * Create the panel.
     */
    public BattleAreaPanel() {
        int tableWidth = 4;
        setLayout(new GridLayout(2, 4, 50, 50));
        for (int i = 0; i < tableWidth; i++) {
            computerInPlayList.add(new JLabel("Computer Card" + i));
            cardMap.put(i + 4, computerInPlayList.get(i));
        }
        computerInPlayList.forEach(x -> x.setBorder(new LineBorder(new Color(0, 0, 0))));
        computerInPlayList.forEach(x -> x.setPreferredSize(new Dimension(100, 100)));
        computerInPlayList.forEach(x -> add(x));
        for (int i = 0; i < tableWidth; i++) { // Initializes the 4 BattleArea slots to the list
            battleAreaButtons.add(new JToggleButton("Player Card" + i));
            add(battleAreaButtons.get(i));
            cardMap.put(i, battleAreaButtons.get(i));
        }
    }
}
