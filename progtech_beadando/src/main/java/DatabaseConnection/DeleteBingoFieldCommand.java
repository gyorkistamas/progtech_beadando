package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class DeleteBingoFieldCommand implements Command{

    private static Logger logger = Logger.getLogger("Delete bingo field logger");

    private static JFrame frame = new JFrame();

    private int id;
    private DatabaseConnection databaseConnection;

    public DeleteBingoFieldCommand(int id, DatabaseConnection databaseConnection) {
        this.id = id;
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {

        try {
            int statusNumber = this.databaseConnection.getDbConnection().createStatement().executeUpdate("delete from bingo_fields where id = '"+ this.id +"'");

            if (statusNumber == 0) {
                logger.warn("Row with id:"+ this.id +" does not exist in the table.");
                JOptionPane.showMessageDialog(frame, this.id + "értékű ID-val ellátott sor nem létezik!", "Hiba a bingo mező törlésekor!" , JOptionPane.WARNING_MESSAGE);
            }
            else {
                logger.info("Delete succeeded!");
            }


        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel a bingo mező törlésekor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
