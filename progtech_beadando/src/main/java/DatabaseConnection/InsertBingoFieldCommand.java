package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;

public class InsertBingoFieldCommand implements Command {

    private static Logger logger = Logger.getLogger("Insert bingo field logger");

    private static JFrame frame = new JFrame();

    private String fieldText;
    private String username;
    private DatabaseConnection databaseConnection;

    public InsertBingoFieldCommand(String fieldText, DatabaseConnection databaseConnection) {
        this.fieldText = fieldText;
        this.databaseConnection = databaseConnection;
    }

    public InsertBingoFieldCommand(String fieldText, String username, DatabaseConnection databaseConnection) {
        this(fieldText, databaseConnection);
        this.username = username;
    }

    @Override
    public void execute() {

        try {

            if (this.username == null) {
                this.databaseConnection.getDbConnection().createStatement().executeUpdate("insert into bingo_fields (field_text) values ('"+ this.fieldText +"')");
                logger.info(this.fieldText + " inserted into database!");
            }
            else {
                this.databaseConnection.getDbConnection().createStatement().executeUpdate("insert into bingo_fields (field_text, uploaded_by) values ('"+ this.fieldText +"', '"+ this.username +"')");
                logger.info(this.fieldText + " inserted into database by: "+ this.username +"!");
            }

        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Insert bingo field error!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
