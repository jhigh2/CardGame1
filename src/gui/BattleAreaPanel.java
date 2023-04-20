package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class BattleAreaPanel extends JPanel {

	public static List<JToggleButton> battleAreaButtons = new ArrayList<>();
	private static List<JLabel> ComputerInPlayList = new ArrayList<>();
	public static HashMap<Integer, JComponent> cardMap = new HashMap<>(8);
	/**
	 * Create the panel.
	 */
	public BattleAreaPanel() {
		int tableWidth = 4;

		setLayout(new GridLayout(2, 4, 50, 50));
		for (int i = 0; i < tableWidth; i++) {
			ComputerInPlayList.add(new JLabel("Computer Card" + i));
			cardMap.put(i + 4, ComputerInPlayList.get(i));
		}
		ComputerInPlayList.forEach(x -> x.setBorder(new LineBorder(new Color(0, 0, 0))));
		ComputerInPlayList.forEach(x -> x.setPreferredSize(new Dimension(100, 100)));
		ComputerInPlayList.forEach(x -> add(x));

		for (int i = 0; i < tableWidth; i++) { // Initializes the 4 BattleArea slots to the list
			battleAreaButtons.add(new JToggleButton("Player Card" + i));
			add(battleAreaButtons.get(i));
			cardMap.put(i, battleAreaButtons.get(i));
		}
	}
}
