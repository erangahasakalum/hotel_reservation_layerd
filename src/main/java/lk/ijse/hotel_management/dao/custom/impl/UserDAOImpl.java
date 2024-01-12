package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.UserDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(UserDto dto) throws SQLException {
        return false;
    }

    @Override
    public  ArrayList<UserDto> getAll() throws SQLException {

        ArrayList<UserDto> arrayList = new ArrayList<>();
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * from user";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            UserDto userDto = new UserDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
            arrayList.add(userDto);
        }
        return arrayList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(UserDto dto) throws SQLException {
        return false;
    }

    @Override
    public  boolean updateUser(String confirmUsername, int userId) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        String sql = "UPDATE user SET userName = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,confirmUsername);
        preparedStatement.setInt(2,userId);

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateUserPassword(String confirmPassword , int userId) throws SQLException {

        Connection connection = DbConnetion.getInstance().getConnection();
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString( 1 , confirmPassword);
        preparedStatement.setInt(2,userId);

        return  preparedStatement.executeUpdate() > 0;
    }
}
