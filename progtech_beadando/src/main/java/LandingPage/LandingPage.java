package LandingPage;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage {

    private static Logger logger = Logger.getLogger("Landing page logger");
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
        logger.info("Created Landing page frame");
        return new JFrame();
    }

    private void ConfigureJFrame(JFrame frame) {
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        logger.info("Landing page frame configured");
    }

    private void RegisterListeners() {
        btnNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("New game button clicked");
                JOptionPane.showMessageDialog(mainPanel, "New game placeholder");
            }
        });
        btnShowResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Result button clicked");
                JOptionPane.showMessageDialog(mainPanel, "Result placeholder");
            }
        });
        btnNewBingoField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("New field button clicked");
                JOptionPane.showMessageDialog(mainPanel, "New field placeholder");
            }
        });

        logger.info("Landing page event listeners registered");
    }

}
