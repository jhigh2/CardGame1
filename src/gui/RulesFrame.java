package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

/**
 * A JFrame explaining the rules of the game
 * @author Joel High
 * @author John Freeman
 */
public class RulesFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RulesFrame frame = new RulesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RulesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StartFrame startFrame = new StartFrame();
				startFrame.setVisible(true);
			}
		});
		contentPane.add(backButton, BorderLayout.SOUTH);
		
		JTextPane rulesTextArea = new JTextPane();
		rulesTextArea.setText("Each player starts with 10 health. \n"
				+ "They are allowed to draw one card per turn and that card will enter their hand.\n"
				+ "If they have enough mana, they may place cards from their hand onto selected slots on the board. \n"
				+ "Once they have placed their cards, they can select 'End Turn' to end their turn.\n"
				+ "Once the turn has ended, all of that players cards will attack the opponents cards.\n"
				+ "If there is no card across from an active card on the board, the card will attack the opponent directly, and the opponent will lose health.");
		contentPane.add(rulesTextArea, BorderLayout.CENTER);
	}

}
