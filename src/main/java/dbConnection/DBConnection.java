package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String url = "jdbc:sqlserver://ALEXANDRU-TOMES;databaseName=ProiectBD";
    private static final String user = "aplicatie";
    private static final String password = "parola123";
    private static Connection connection;


    private DBConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
