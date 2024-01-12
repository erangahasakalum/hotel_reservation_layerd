package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.RoomCategoryDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.RoomsCategoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomCategoryDAOImpl implements RoomCategoryDAO {
    @Override
    public  boolean save(RoomsCategoryDto dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "INSERT INTO rooms_category (id,name,description) values (?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDescription());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    @Override
    public  List<RoomsCategoryDto> getAll() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM rooms_category";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<RoomsCategoryDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new RoomsCategoryDto
                            (resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3)

                            ));
        }
        return dtoList;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RoomsCategoryDto dto) throws SQLException {
        return false;
    }

    @Override
    public  String getCategoryName(int categoryID) throws SQLException {
        String name = null ;
        Connection connection = DbConnetion.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT name FROM rooms_category WHERE id = ?");
        pstm.setInt(1, categoryID);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            name = resultSet.getString(1);

        }
        return name;
    }
    @Override
    public  int getCategoryID(String category) throws SQLException {
        int id = 0;
        Connection connection = DbConnetion.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM rooms_category WHERE name = ?");
        pstm.setString(1, category);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt(id);

        }
        return id;
    }
}
