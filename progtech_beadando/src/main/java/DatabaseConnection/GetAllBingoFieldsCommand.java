package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllBingoFieldsCommand implements Command{

    private static Logger logger = Logger.getLogger("Get all bingo fields logger");

    private static JFrame frame = new JFrame();

    private DatabaseConnection databaseConnection;

    private ResultSet results;

    private ArrayList<String> listOfBingoFieldTexts;

    public GetAllBingoFieldsCommand(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        listOfBingoFieldTexts = new ArrayList<String>();
    }


    public ArrayList<String> getListOfBingoFieldTexts() {
        return listOfBingoFieldTexts;
    }

    @Override
    public void execute() {
        try {

            this.results = this.databaseConnection.getDbConnection().createStatement().executeQuery("select * from bingo_fields");

            while (this.results.next()) {
                listOfBingoFieldTexts.add(this.results.getString("field_text"));
            }

            logger.info("Query succeeded!");
        }
        catch (Exception e) {
            logger.error(e.getMessage());

            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázisbeli hiba történt a bingo mező értékeinek lekérdezésekor!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
