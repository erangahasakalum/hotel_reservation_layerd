package lk.ijse.hotel_management.dao;

import lk.ijse.hotel_management.db.DbConnetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static  <T>T test(String sql, Object... objects) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnetion.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1,objects[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }else {
            return (T) (Boolean)(preparedStatement.executeUpdate() > 0 );
        }
    }
}
