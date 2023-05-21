package BingoGame;

import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.GetAllBingoFieldsCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BingoObserverTest {

    BingoObserver observer = new BingoObserver(new GetAllBingoFieldsCommand(new DatabaseConnection("jdbc:mysql://localhost:3306/progtech", "main", "password")));

    private Boolean[][] noneTrue = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    };

    private Boolean[][] lineTrue = {
            {true, true, true, true, true},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    };

    private Boolean[][] columnTrue = {
            {false, true, false, false, false},
            {false, true, false, false, false},
            {false, true, false, false, false},
            {false, true, false, false, false},
            {false, true, false, false, false}
    };

    private Boolean[][] diagonalTrue = {
            {true, false, false, false, false},
            {false, true, false, false, false},
            {false, false, true, false, false},
            {false, false, false, true, false},
            {false, false, false, false, true}
    };

    private Boolean[][] sideDiagonalTrue = {
            {false, false, false, false, true},
            {false, false, false, true, false},
            {false, false, true, false, false},
            {false, true, false, false, false},
            {true, false, false, false, false}
    };

    private Boolean[][] randomTrues = {
            {true, false, false, true, true},
            {false, false, false, false, false},
            {true, false, true, true, false},
            {false, false, false, false, false},
            {true, false, false, false, true}
    };

    @Test
    void hasRowFullOfTrue() {
        Assertions.assertFalse(observer.hasRowFullOfTrue(noneTrue));
        Assertions.assertTrue(observer.hasRowFullOfTrue(lineTrue));
        Assertions.assertFalse(observer.hasRowFullOfTrue(randomTrues));
    }

    @Test
    void hasColumnFullOfTrue() {
        Assertions.assertFalse(observer.hasColumnFullOfTrue(noneTrue));
        Assertions.assertTrue(observer.hasColumnFullOfTrue(columnTrue));
        Assertions.assertFalse(observer.hasColumnFullOfTrue(randomTrues));
    }

    @Test
    void hasDiagonalFullOfTrue() {
        Assertions.assertFalse(observer.hasDiagonalFullOfTrue(noneTrue));
        Assertions.assertTrue(observer.hasDiagonalFullOfTrue(diagonalTrue));
        Assertions.assertTrue(observer.hasDiagonalFullOfTrue(sideDiagonalTrue));
        Assertions.assertFalse(observer.hasDiagonalFullOfTrue(randomTrues));
        Assertions.assertTrue(observer.hasDiagonalFullOfTrue(sideDiagonalTrue));
    }
}