package LandingPage;

import AddNewBingoField.AddNewBingoField;
import BingoGame.GameStarter;
import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.GetAllBingoFieldsCommand;
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
                StartGame();
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
//                JOptionPane.showMessageDialog(mainPanel, "New field placeholder");
                AddNewBingoField aNBF = new AddNewBingoField(new DatabaseConnection(
                        "jdbc:mysql://localhost:3306/progtech",
                        "main",
                        "password"
                ));
            }
        });

        logger.info("Landing page event listeners registered");
    }

    private void StartGame() {
        int numberOfTables = 0;

        try {
            numberOfTables = Integer.parseInt(txtNumberOfBoards.getText());
        }
        catch (Exception e) {
            logger.error("Not a number provided");
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Számot adjon meg!",
                    "Hiba",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        if(numberOfTables < 1 || numberOfTables > 10) {
            logger.error("Bad table number provided");
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Kérjük, 1 és 10 közötti számot adjon meg!");
            return;
        }

        GetAllBingoFieldsCommand com = new GetAllBingoFieldsCommand(
                new DatabaseConnection("jdbc:mysql://localhost:3306/progtech",
                        "main",
                        "password"
        ));

        com.execute();

        if (com.getListOfBingoFieldTexts().size() < 25) {
            logger.error("Not enough fields to generate table");
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Az adatbázis nem tartalmaz elég elemet egy tábla létrehozásához!",
                    "Hiba", JOptionPane.ERROR_MESSAGE);

            return;
        }

        logger.info("Game starting.");
        GameStarter game = new GameStarter(numberOfTables);
    }

}
