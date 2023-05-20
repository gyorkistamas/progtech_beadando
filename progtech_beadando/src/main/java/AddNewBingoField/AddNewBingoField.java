package AddNewBingoField;

import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.InsertBingoFieldCommand;
import DatabaseConnection.GetAllBingoFieldsCommand;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddNewBingoField {

    private Logger logger = Logger.getLogger("Add new bingo field logger");
    private JPanel panelMain;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblField;
    private JTextField txtField;
    private JButton btnAddBingoField;
    private JButton btnCancel;
    private JFrame frame;

    public static final String EMPTY_STRING = "";

    private DatabaseConnection databaseConnection;

    public AddNewBingoField(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
        ConfigureFrame();
        RegisterListeners();
    }

    private void ConfigureFrame() {
        this.frame = new JFrame();
        frame.setContentPane(this.panelMain);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
        logger.info("Add new bingo field frame configured!");
    }

    private void RegisterListeners() {

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Cancel button clicked");
                Cancel();

            }
        });

        btnAddBingoField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Add new bingo field button clicked");

                String bingoFieldText = txtField.getText();
                String uploader = txtName.getText();

                if (bingoFieldText.length() <= 2) {
                    logger.warn("Length of bingo field text must be higher than 2!");
                    JOptionPane.showMessageDialog(frame, "A bingo mező értékének hossza nem lehet kisebb 3-nál!", "Figyelmeztetés!", JOptionPane.WARNING_MESSAGE);
                    ClearInputs(txtField, txtName);
                }
                else {

                    if (uploader.isEmpty()) {
                        Add(bingoFieldText);
                    }
                    else {
                        Add(bingoFieldText, uploader);
                    }
                }

            }
        });

        logger.info("Add new bingo field frame event listeners registered!");

    }

    private void Add(String bingoFieldText) {

        if (!Exists(bingoFieldText)) {

            InsertBingoFieldCommand insertBingoField = new InsertBingoFieldCommand(
                    bingoFieldText,
                    this.databaseConnection
            );

            insertBingoField.execute();
            this.frame.dispose();
        }

    }

    private void Add(String bingoFieldText, String uploader) {

        if (!Exists(bingoFieldText)) {
            InsertBingoFieldCommand insertBingoField = new InsertBingoFieldCommand(
                    bingoFieldText,
                    uploader,
                    this.databaseConnection
            );
            insertBingoField.execute();
            this.frame.dispose();
        }


    }

    private boolean Exists(String field) {
        GetAllBingoFieldsCommand getAllBingoFields = new GetAllBingoFieldsCommand(
                this.databaseConnection
        );

        getAllBingoFields.execute();

        if (getAllBingoFields.getListOfBingoFieldTexts().contains(field)) {
            logger.warn(field + " already exists!");
            JOptionPane.showMessageDialog(frame, field + "értékű mező már létezik!", "Figyelmeztetés!", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private void ClearInputs(JTextField... params) {

        for (int i = 0; i < params.length; i++) {
            params[i].setText(EMPTY_STRING);
        }

    }

    private void Cancel() {
        logger.info("Add new bingo field frame closed!");
        this.frame.dispose();
    }

}
