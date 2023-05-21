package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;

public class GetAllScoresCommand implements Command{


    private Logger logger = Logger.getLogger("Get all scores logger");

    private JFrame frame = new JFrame();

    private DatabaseConnection databaseConnection;

    private ResultSet resultSet;

    public GetAllScoresCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    @Override
    public void execute() {

        try {
            this.setResultSet(this.databaseConnection.getDbConnection().createStatement().executeQuery("select username, time_of_win from score"));
            logger.info("Query succeeded!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba történt a pontszámok lekérdezésekor!", JOptionPane.ERROR_MESSAGE);
        }


    }
}
