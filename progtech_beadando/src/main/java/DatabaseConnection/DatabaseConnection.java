package DatabaseConnection;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Logger logger = Logger.getLogger("Database Connection logger");
    private Connection dbConnection;

    private JPanel mainPanel;

    public DatabaseConnection(String url, String username, String password) {

        Connection connection;
        try {

           connection = DriverManager.getConnection(url, username, password);

        }
        catch (Exception e) {

            connection = null;
            logger.warn(e.getMessage());
            System.exit(0);

        }

        this.setDbConnection(connection);
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

}
