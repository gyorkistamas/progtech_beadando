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
                JOptionPane.showMessageDialog(frame, this.fieldText + " beszúrásra került az adatbázisba!", "Információ", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                this.databaseConnection.getDbConnection().createStatement().executeUpdate("insert into bingo_fields (field_text, uploaded_by) values ('"+ this.fieldText +"', '"+ this.username +"')");
                logger.info(this.fieldText + " inserted into database by: "+ this.username +"!");
                JOptionPane.showMessageDialog(frame, this.fieldText + " beszúrásra került az adatbázisba "+ this.username +" által!", "Információ", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (Exception e) {
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba lépett fel a bingó mező beszúrásakor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
