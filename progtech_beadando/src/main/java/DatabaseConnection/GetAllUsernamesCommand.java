package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllUsernamesCommand implements Command {

    private static Logger logger = Logger.getLogger("Get all usernames logger");

    private static JFrame frame = new JFrame();

    private DatabaseConnection databaseConnection;

    private ResultSet results;

    private ArrayList<String> listOfUsernames;

    public GetAllUsernamesCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        listOfUsernames = new ArrayList<String>();
    }

    public ArrayList<String> getListOfUsernames() {
        return listOfUsernames;
    }

    @Override
    public void execute() {
        try {

            this.results = this.databaseConnection.getDbConnection().createStatement().executeQuery("select username from score");

            while (this.results.next()) {
                listOfUsernames.add(this.results.getString("username"));
            }
            logger.info("Query succeeded!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            JOptionPane.showMessageDialog(frame, e.getMessage(), "Get all username error!", JOptionPane.ERROR_MESSAGE);
        }
    }


}
