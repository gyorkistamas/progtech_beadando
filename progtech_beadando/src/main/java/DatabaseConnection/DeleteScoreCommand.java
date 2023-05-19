package DatabaseConnection;

import org.apache.log4j.Logger;

public class DeleteScoreCommand implements Command{

    private Logger logger = Logger.getLogger("Delete score logger");
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
                logger.info("Row with id: "+ this.id +" does not exist in the table.");
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
