package DatabaseConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class DeleteScoreCommandTest {

    @Test
    void shouldDeleteScore() {
        GetAllScoresCommand command = new GetAllScoresCommand(new DatabaseConnection(
                "jdbc:mysql://localhost:3306/progtech",
                "main",
                "password"
                ));
        command.execute();
        ResultSet dataBeforeDeletion = command.getResultSet();

        DeleteScoreCommand delComm = new DeleteScoreCommand(1, new DatabaseConnection(
                "jdbc:mysql://localhost:3306/progtech",
                "main",
                "password"
        ));

        delComm.execute();


        GetAllScoresCommand command2 = new GetAllScoresCommand(new DatabaseConnection(
                "jdbc:mysql://localhost:3306/progtech",
                "main",
                "password"
        ));
        command.execute();
        ResultSet dataAfterDeletion = command.getResultSet();

        int countBeforeDeletion = 0;

        try {
            while (dataBeforeDeletion.next()) {
                countBeforeDeletion++;
            }
        }
        catch (Exception ignored) {

        }

        int countAfterDeletion = 0;

        try {
            while (dataAfterDeletion.next()) {
                countAfterDeletion++;
            }
        }
        catch (Exception ignored) {

        }
        
        Assertions.assertEquals(countBeforeDeletion - 1, countAfterDeletion);

    }

}