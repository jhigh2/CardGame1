package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartFrame extends JFrame {

    /**
     * Create the frame.
     */
    public StartFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JButton startButton = newStartButton();
        contentPane.add(startButton);
        JButton rulesButton = newRulesButton();
        contentPane.add(rulesButton);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
			try {
				StartFrame frame = new StartFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
    }

    private JButton newRulesButton() {
        JButton rulesButton = new JButton("Rules");
        rulesButton.addActionListener(e -> {
            dispose();
            RulesFrame rulesFrame = new RulesFrame();
            rulesFrame.setVisible(true);
        });
        return rulesButton;
    }

    private JButton newStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            dispose();
            GuiBoard gameFrame = new GuiBoard();
            gameFrame.setVisible(true);
        });
        return startButton;
    }
}
