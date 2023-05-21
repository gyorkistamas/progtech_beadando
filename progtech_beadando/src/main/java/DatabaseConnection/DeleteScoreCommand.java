package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class DeleteScoreCommand implements Command{

    private static Logger logger = Logger.getLogger("Delete score logger");
    private static JFrame frame = new JFrame();
    private int id;
    private DatabaseConnection databaseConnection;

    public DeleteScoreCommand(int id, DatabaseConnection databaseConnection) {
        this.id = id;
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {

        try {
            int statusNumber = this.databaseConnection.getDbConnection().createStatement().executeUpdate("delete from score where id = '"+ this.id +"'");

            if (statusNumber == 0) {
                logger.warn("Row with id: "+ this.id +" does not exist in the table.");
                JOptionPane.showMessageDialog(frame, this.id + "értékű ID-val ellátott sor nem létezik!", "Hiba pontszám törléskor!", JOptionPane.WARNING_MESSAGE);
            }
            else {
                logger.info("Delete succeeded!");
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel a pontszám törlésekor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
