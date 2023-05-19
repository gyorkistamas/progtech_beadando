package DatabaseConnection;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class DeleteBingoFieldCommand implements Command{

    private Logger logger = Logger.getLogger("Delete bingo field logger");
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
                logger.info("Row with id:"+ this.id +" does not exist in the table.");
            }
            else {
                logger.info("Delete succeeded!");
            }


        }
        catch (Exception e) {
            logger.warn(e.getMessage());
        }

    }
}
