package DatabaseConnection;

import org.apache.log4j.Logger;

public class InsertScoreCommand implements Command {

    private Logger logger = Logger.getLogger("Insert score logger");
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
            logger.info(this.username + " inserted into database!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
