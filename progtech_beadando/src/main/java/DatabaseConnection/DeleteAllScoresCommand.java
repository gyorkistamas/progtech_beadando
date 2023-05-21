package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class DeleteAllScoresCommand implements Command{

    private Logger logger = Logger.getLogger("Delete all scores logger");
    private static JFrame frame = new JFrame();
    private DatabaseConnection databaseConnection;

    public DeleteAllScoresCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {

        try {
            this.databaseConnection.getDbConnection().createStatement().executeUpdate("delete from score");

            logger.info("All rows deleted!");
            JOptionPane.showMessageDialog(frame, "Pontszám tábla kiürítve!", "Információ", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel a pontszám tábla kiürítésekor!", JOptionPane.ERROR_MESSAGE);
        }


    }
}
