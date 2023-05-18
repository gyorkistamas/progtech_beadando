package LandingPage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage {

    private JPanel mainPanel;
    private JButton btnNewGame;
    private JTextField txtNumberOfBoards;
    private JButton btnShowResults;
    private JButton btnNewBingoField;
    private JButton btnRegister;
    private JPasswordField txtFieldPassword;

    public LandingPage() {
        JFrame frame = CreateFrame();
        ConfigureJFrame(frame);
        RegisterListeners();
    }

    private JFrame CreateFrame() {
        return new JFrame();
    }

    private void ConfigureJFrame(JFrame frame) {
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void RegisterListeners() {
        btnNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "New game placeholder");
            }
        });
        btnShowResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "Result placeholder");
            }
        });
        btnNewBingoField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "New field placeholder");
            }
        });
    }

}
