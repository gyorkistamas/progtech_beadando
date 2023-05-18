package DatabaseConnection;

import org.apache.log4j.Logger;

import java.sql.ResultSet;

public class GetAllBingoFieldsCommand implements Command{

    private Logger logger = Logger.getLogger("Get all bingo fields logger");
    private DatabaseConnection databaseConnection;

    private ResultSet results;

    public GetAllBingoFieldsCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void execute() {
        try {

            this.results = this.databaseConnection.getDbConnection().createStatement().executeQuery("select * from bingo_fields");

            try
            {
                while (this.results.next()) {
                    System.out.println(this.results.getString("field_text"));
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
