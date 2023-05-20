package BingoGame;

import DatabaseConnection.GetAllBingoFieldsCommand;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class BingoTable extends Observable {
    private JButton btn0_0;
    private JButton btn0_1;
    private JButton btn0_2;
    private JButton btn0_3;
    private JButton btn0_4;
    private JButton btn1_0;
    private JButton btn2_0;
    private JButton btn3_0;
    private JButton btn4_0;
    private JButton btn1_1;
    private JButton btn1_2;
    private JButton btn1_3;
    private JButton btn1_4;
    private JButton btn2_1;
    private JButton btn2_2;
    private JButton btn2_3;
    private JButton btn2_4;
    private JButton btn3_1;
    private JButton btn3_2;
    private JButton btn3_3;
    private JButton btn3_4;
    private JButton btn4_1;
    private JButton btn4_2;
    private JButton btn4_3;
    private JButton btn4_4;
    private JPanel mainPanel;

    private JFrame frame;

    private static Logger logger = Logger.getLogger("Bingo table logger");

    public JButton[][] buttons = {
            { btn0_0, btn0_1, btn0_2, btn0_3, btn0_4 },
            { btn1_0, btn1_1, btn1_2, btn1_3, btn1_4 },
            { btn2_0, btn2_1, btn2_2, btn2_3, btn2_4 },
            { btn3_0, btn3_1, btn3_2, btn3_3, btn3_4 },
            { btn4_0, btn4_1, btn4_2, btn4_3, btn4_4 }
    };

    public Boolean[][] isClickedMatrix = {
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false }
    };
    private ArrayList<String> possibleFields = new ArrayList<>();

    public BingoTable(GetAllBingoFieldsCommand fieldsCommand) {
        populatePossibleFields(fieldsCommand);
        getRandomButtonTexts();
        registerEventHandlers();
    }

    private void populatePossibleFields(GetAllBingoFieldsCommand fieldsCommand) {
        fieldsCommand.execute();
        possibleFields = fieldsCommand.getListOfBingoFieldTexts();
    }

    private void getRandomButtonTexts() {
        Random rnd = new Random();
        for (JButton[] buttonRow: buttons) {
            for (JButton button: buttonRow) {
                int index = rnd.nextInt(possibleFields.size());
                button.setText(possibleFields.get(index));
                possibleFields.remove(index);
            }
        }
    }

    private void registerEventHandlers() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        logger.info("Clicked button");
                        setChanged();
                        notifyObservers(new BingoFieldClickArg(finalI, finalJ, buttons[finalI][finalJ].getText()));
                    }
                });
            }
        }
    }

    public void showWindow() {
        CreateFrame();
        ConfigureJFrame();
    }

    private void CreateFrame() {
        this.frame =  new JFrame();
    }

    private void ConfigureJFrame() {
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void closeTable() {
        this.frame.dispose();
    }
}
