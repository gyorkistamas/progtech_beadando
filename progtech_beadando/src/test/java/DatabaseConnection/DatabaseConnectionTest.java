package DatabaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection = new DatabaseConnection(
            "jdbc:mysql://localhost:3306/bingo_progtech",
            "root",
            ""
    );

    @Test
    void DatabaseConnectionShouldInstantiate() {

        DatabaseConnection testDBcon = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/bingo_progtech",
                "root",
                ""
        );

        assertNotNull(testDBcon);

    }

    @Test
    void DatabaseConnectionShouldExistAsAnInstance() throws SQLException{

        DatabaseConnection testDBcon = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/bingo_progtech",
                "root",
                ""
        );

        assertEquals(
                new DatabaseConnection(
                        "jdbc:mysql://localhost:3306/bingo_progtech",
                "root",
                ""
                ).getDbConnection().getMetaData().getURL(),
                testDBcon.getDbConnection().getMetaData().getURL()
        );

        assertEquals(
                new DatabaseConnection(
                        "jdbc:mysql://localhost:3306/bingo_progtech",
                        "root",
                        ""
                ).getDbConnection().getMetaData().getUserName(),
                testDBcon.getDbConnection().getMetaData().getUserName()
        );

    }

    @Test
    void BingoFieldAfterInsertionShouldBeInDatabase() {

        String uniqueText = UUID.randomUUID().toString();

        InsertBingoFieldCommand insertBingoField = new InsertBingoFieldCommand(
                uniqueText,
                this.databaseConnection
        );
        insertBingoField.execute();

        GetAllBingoFieldsCommand getAllBingoFields = new GetAllBingoFieldsCommand(
                this.databaseConnection
        );
        getAllBingoFields.execute();

        assertTrue(getAllBingoFields.getListOfBingoFieldTexts().contains(uniqueText));

    }

    @Test
    void ScoreAfterInsertionShouldBeInDatabase() throws SQLException {

        String uniqueName = UUID.randomUUID().toString().substring(0, 5).toUpperCase();

        InsertScoreCommand insertScore = new InsertScoreCommand(
                uniqueName,
                this.databaseConnection
        );

        insertScore.execute();

        GetAllUsernamesCommand getAllUsernames = new GetAllUsernamesCommand(
                this.databaseConnection
        );
        getAllUsernames.execute();

        assertTrue(getAllUsernames.getListOfUsernames().contains(uniqueName));

    }

    @Test
    void BingoFieldsTableShouldBeEmptyAfterWipe() {

        DeleteAllBingoFieldsCommand deleteAllBingoFields = new DeleteAllBingoFieldsCommand(
                this.databaseConnection
        );
        deleteAllBingoFields.execute();

        GetAllBingoFieldsCommand getAllBingoFields = new GetAllBingoFieldsCommand(
                this.databaseConnection
        );
        getAllBingoFields.execute();

        assertTrue(getAllBingoFields.getListOfBingoFieldTexts().size() == 0);

    }



}