package UploadResult;

import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.InsertScoreCommand;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UploadResult {
    private JTextField txtFieldName;
    private JButton btnSave;
    private JButton btnCancel;
    private JPanel mainPanel;
    private JFrame frame;

    private DatabaseConnection con;

    public UploadResult(DatabaseConnection con) {
        this.con = con;
        ConfigureJFrame();
        registerListeners();

    }

    private void ConfigureJFrame() {
        frame = new JFrame();
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void registerListeners() {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveScore();
            }
        });

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exit();
            }
        });
    }

    private void saveScore() {
        if (txtFieldName.getText().length() == 0) {
            JOptionPane.showMessageDialog(frame, "A név nem lehet üres!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            InsertScoreCommand command = new InsertScoreCommand(txtFieldName.getText(), con);

            command.execute();
            JOptionPane.showMessageDialog(frame, "Az eredmény elmentve!");

            this.frame.dispose();
        }
    }

    private void exit() {
        this.frame.dispose();
    }
}
