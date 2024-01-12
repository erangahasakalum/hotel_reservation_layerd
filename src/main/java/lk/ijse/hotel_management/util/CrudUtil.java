package lk.ijse.hotel_management.util;



import lk.ijse.hotel_management.db.DbConnetion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object... args) throws SQLException {
        PreparedStatement pstm = DbConnetion.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1), args[i]);
        }

        if(sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) pstm.executeQuery(); // ResultSet
        }
        return (T) (Boolean)(pstm.executeUpdate() > 0); //Boolean
    }
}
