package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * A JFrame with buttons prompting the user if they would like to play again
 * @author Joel High
 * @author John Freeman
 */
public class PlayAgainFrame extends JFrame {

	public JLabel lblPlayAgain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				PlayAgainFrame frame = new PlayAgainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayAgainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblPlayAgain = new JLabel();
		lblPlayAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayAgain.setPreferredSize(new Dimension(800, 200));
		contentPane.add(lblPlayAgain, BorderLayout.NORTH);

		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(e -> {
			dispose();
			GuiBoard guiBoard = new GuiBoard();
			guiBoard.resetGuiBoard();
			guiBoard.updateBoard();
			guiBoard.setVisible(true);
		});
		btnYes.setPreferredSize(new Dimension(400, 400));
		contentPane.add(btnYes, BorderLayout.CENTER);

		JButton btnNo = new JButton("No");
		btnNo.addActionListener(e -> System.exit(0));
		btnNo.setPreferredSize(new Dimension(400, 400));
		contentPane.add(btnNo, BorderLayout.EAST);
	}

}
