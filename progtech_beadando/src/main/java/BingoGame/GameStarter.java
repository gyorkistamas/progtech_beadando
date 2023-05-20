package BingoGame;

import DatabaseConnection.DatabaseConnection;
import DatabaseConnection.GetAllBingoFieldsCommand;
import org.apache.log4j.Logger;

public class GameStarter {
    private int numberOfBoards;

    private static Logger logger = Logger.getLogger("Gamestarter logger");

    public GameStarter(int numberOfBoards) {
        this.numberOfBoards = numberOfBoards;
        BingoObserver game = CreateObserver();
        CreateTables(game);
        game.startGame();
    }

    private void CreateTables(BingoObserver gameObserver) {

        for (int i = 0; i < numberOfBoards; i++) {
            BingoTable table = CreateTable();
            table.addObserver(gameObserver);
            gameObserver.AddTable(table);
        }
        logger.info("Created " + this.numberOfBoards + " bingo tables");
    }

    private BingoTable CreateTable() {
        logger.info("Creating Bingo Table");
        return new BingoTable(new GetAllBingoFieldsCommand(
                                new DatabaseConnection("jdbc:mysql://localhost:3306/progtech",
                                                  "main",
                                                  "password"
        )));
    }

    private BingoObserver CreateObserver() {
        logger.info("Creating BingoObserver");
        return new BingoObserver(new GetAllBingoFieldsCommand(
                                    new DatabaseConnection("jdbc:mysql://localhost:3306/progtech",
                                                      "main",
                                                      "password"
        )));
    }

}
