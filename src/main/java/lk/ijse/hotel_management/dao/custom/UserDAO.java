package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<UserDto> {

    boolean updateUser(String confirmUsername, int userId) throws SQLException;

    boolean updateUserPassword(String confirmPassword, int userId) throws SQLException;
}
