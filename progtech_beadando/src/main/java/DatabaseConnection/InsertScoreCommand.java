package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class InsertScoreCommand implements Command {

    private static Logger logger = Logger.getLogger("Insert score logger");

    private static JFrame frame = new JFrame();

    private String username;
    private DatabaseConnection databaseConnection;

    public InsertScoreCommand(String username, DatabaseConnection databaseConnection) {
        this.username = username;
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {

        try {
            this.databaseConnection.getDbConnection().createStatement().executeUpdate("insert into score (username) values ('"+this.username+"')");
            logger.info(this.username + " beszúrásra került az adatbázisba!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel az eredmény beszúrásakor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
