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
                JOptionPane.showMessageDialog(frame, "Row with id:"+ this.id +" does not exist in the table.", "Delete bingo field warning!" , JOptionPane.WARNING_MESSAGE);
            }
            else {
                logger.info("Delete succeeded!");
            }


        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Delete bingo field error!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
