package lk.ijse.hotel_management.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnetion {

    private static DbConnetion dbConnetion;
    private Connection connection;

    private DbConnetion() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel",
                "root",
                "ijse1234"
        );
    }

    public static DbConnetion getInstance() throws SQLException {
        if (dbConnetion == null) {
            dbConnetion = new DbConnetion();
            return dbConnetion;
        } else {
            return dbConnetion;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
