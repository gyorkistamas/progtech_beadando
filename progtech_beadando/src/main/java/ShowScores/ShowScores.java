package ShowScores;


import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.GetAllScoresCommand;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ShowScores {

    private Logger logger = Logger.getLogger("Show scores logger");
    private JPanel panelMain;
    private JScrollPane scrollPane;
    private JTable tableScores;
    private JButton btnRefresh;
    private JButton btnClear;
    private JButton btnCancel;
    private JFrame frame;
    private DatabaseConnection databaseConnection;

    public ShowScores(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
        ConfigureFrame();
        CreateTable();
        RegisterListeners();
    }

    private void ConfigureFrame() {
        this.frame = new JFrame();
        frame.setContentPane(this.panelMain);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

        logger.info("Show scores frame configured!");
    }

    private void RegisterListeners() {

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cancel();
            }
        });

        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClearTable();
            }
        });

        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RefreshTable();
            }
        });

        logger.info("Show scores listeners registered!");

    }

    private void CreateTable() {

        GetAllScoresCommand getAllScoresCommand = new GetAllScoresCommand(
                this.databaseConnection
        );
        getAllScoresCommand.execute();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Username");
        model.addColumn("Time_Of_Win");

//        this.tableScores.setModel(model);

        try {

            ResultSet resultSet = getAllScoresCommand.getResultSet();

            while (resultSet.next()) {

                String username = resultSet.getString("username");
                Timestamp time_of_win = resultSet.getTimestamp("time_of_win");

                String[] row = { username, time_of_win.toString() };

                model.addRow(row);

            }

            this.tableScores.setModel(model);

        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba a pontszámok lekérdezésekor!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        logger.info("Scores table created!");
    }

    private void RefreshTable() {
        logger.info("Scores table refreshed!");
        ClearTable();
        CreateTable();

    }


    private void ClearTable() {
        this.tableScores.setModel(new DefaultTableModel());
        logger.info("Scores table cleared!");
    }

    private void Cancel() {
        this.frame.dispose();
        logger.info("Show scores frame closed!");
    }

}
