package BingoGame;

import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.GetAllBingoFieldsCommand;
import UploadResult.UploadResult;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class BingoObserver implements Observer {
    private static Logger logger = Logger.getLogger("Bingo observer logger");

    private ArrayList<BingoTable> tables = new ArrayList<>();

    private ArrayList<String> pickedFields = new ArrayList<>();

    private ArrayList<String> canBePickedFields = new ArrayList<>();

    private Thread generatorThread;
    private JLabel labelGeneratedValue;
    private JLabel timeUntilNextRoll;
    private JPanel mainPanel;

    private JFrame frame;

    public void AddTable(BingoTable table) {
        tables.add(table);
    }

    public BingoObserver(GetAllBingoFieldsCommand bingoFieldsCommand) {
        getPossibleFields(bingoFieldsCommand);
    }

    private void getPossibleFields(GetAllBingoFieldsCommand command) {
        command.execute();
        canBePickedFields = command.getListOfBingoFieldTexts();
    }
    public void startGame() {
        logger.info("Starting game");
        showWindow();
        showTables();
        startThread();
    }

    private void startThread() {
        logger.info("Start thread for generation");
        generatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                generation();
            }
        });

        generatorThread.start();
    }

    private void generation() {
        for (int i = 5; i >= 5; i--) {
            timeUntilNextRoll.setText(String.valueOf(i));
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                logger.error("Error when sleeping thread: " + e.getMessage());
            }
        }

        Random rnd = new Random();
        int numberOfGenerations = 25;
        while(canBePickedFields.size() > 0 && numberOfGenerations > 0) {
            int index = rnd.nextInt(canBePickedFields.size());
            pickedFields.add(canBePickedFields.get(index));
            labelGeneratedValue.setText(canBePickedFields.get(index));
            canBePickedFields.remove(index);

            for (int i = 3; i >= 0; i--) {
                timeUntilNextRoll.setText(String.valueOf(i));
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    logger.error("Error when sleeping thread: " + e.getMessage());
                }
            }
            numberOfGenerations--;
        }

        endGame(false);
    }

    private void showTables() {
        logger.info("Showing tables for game");
        for (BingoTable table: tables) {
            table.showWindow();
        }
    }

    private void showWindow() {
        frame = new JFrame();
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.info("Observer update running");
        BingoFieldClickArg argument = (BingoFieldClickArg)arg;
        BingoTable table = (BingoTable)o;
        if (pickedFields.contains(argument.getButtonText())) {
            logger.info("Button press is correct");
            table.isClickedMatrix[argument.getX()][argument.getY()] = true;
            table.buttons[argument.getX()][argument.getY()].setEnabled(false);
        }

        if (isWinning(table)) {
            endGame(true);
        }
    }


    private void endGame(boolean wining) {

        for (BingoTable table: tables) {
            table.closeTable();
        }
        if (wining) {
            UploadResult result = new UploadResult(
                    new DatabaseConnection("jdbc:mysql://localhost:3306/progtech",
                                      "main",
                                      "password"
            ));
            logger.info("Game won!");
        }
        else {
            JOptionPane.showMessageDialog(frame, "Vesztett!");
            logger.info("Game lost!");
        }

        this.frame.dispose();
        generatorThread.stop();
    }

    private Boolean isWinning(BingoTable table) {
        return  hasRowFullOfTrue(table.isClickedMatrix)    ||
                hasColumnFullOfTrue(table.isClickedMatrix) ||
                hasDiagonalFullOfTrue(table.isClickedMatrix);
    }

    public Boolean hasRowFullOfTrue(Boolean[][] table) {
        for (int i = 0; i < table.length; i++) {
            boolean rowIsFullOfTrue = true;
            for (int j = 0; j < table[0].length; j++) {
                if (!table[i][j]) {
                    rowIsFullOfTrue = false;
                    break;
                }
            }
            if (rowIsFullOfTrue) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasColumnFullOfTrue(Boolean[][] table) {
        for (int i = 0; i < table.length; i++) {
            if (!table[i][0]) {
                return false;
            }
        }
        return true;
    }

    public Boolean hasDiagonalFullOfTrue(Boolean[][] table) {
        boolean mainDiagonal = true;
        boolean sideDiagonal = true;
        for (int i = 0; i < table.length; i++) {
            if (!table[i][i]) {
                mainDiagonal = false;
            }

            if (!table[i][table.length - 1 - i]) {
                sideDiagonal = false;
            }
        }
        return mainDiagonal || sideDiagonal;
    }
}
