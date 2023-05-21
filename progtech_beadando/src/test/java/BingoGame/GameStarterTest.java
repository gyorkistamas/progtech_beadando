package BingoGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStarterTest {

    @Test
    void createTables() {
        GameStarter starter = new GameStarter(10);
        BingoObserver observer = new BingoObserver();
        starter.CreateTables(observer);
        Assertions.assertEquals(10, observer.tables.size());
    }

    @Test
    void createTable() {
        GameStarter starter = new GameStarter(1);
        Assertions.assertNotNull(starter.CreateTable());
    }

    @Test
    void createObserver() {
        GameStarter starter = new GameStarter(1);
        Assertions.assertNotNull(starter.CreateObserver());
    }
}