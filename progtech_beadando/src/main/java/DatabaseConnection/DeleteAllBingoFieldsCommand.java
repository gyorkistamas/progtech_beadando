package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class DeleteAllBingoFieldsCommand implements Command{

    private Logger logger = Logger.getLogger("Delete all bingo fields logger");

    private DatabaseConnection databaseConnection;

    private JFrame frame = new JFrame();

    public DeleteAllBingoFieldsCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {

        try {
            this.databaseConnection.getDbConnection().createStatement().executeUpdate("delete from bingo_fields");

            logger.info("All rows deleted!");
            JOptionPane.showMessageDialog(frame, "Bingo mezők tábla kiürítve!", "Információ", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel a bingó mezők tábla kiürítésekor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
