package DatabaseConnection;

import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class GetAllUsernamesCommand implements Command {

    private Logger logger = Logger.getLogger("Get all usernames logger");
    private DatabaseConnection databaseConnection;
    private ResultSet results;

    public GetAllUsernamesCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {
        try {

            this.results = this.databaseConnection.getDbConnection().createStatement().executeQuery("select username from score");

            try
            {
                while (this.results.next()) {
                    System.out.println(this.results.getString("username"));
                }
                logger.info("Query succeeded!");
            }
            catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        catch (Exception e) {

            logger.warn(e.getMessage());

        }
    }


}
