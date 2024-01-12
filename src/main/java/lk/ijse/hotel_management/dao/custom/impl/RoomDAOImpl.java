package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.RoomDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.RoomsDto;
import lk.ijse.hotel_management.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public  boolean save(RoomsDto dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(
                "INSERT INTO rooms VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

        pstm.setInt(1, dto.getId());
        pstm.setString(2, dto.getRoom_number());
        pstm.setDouble(3, dto.getRoom_price());
        pstm.setInt(4, dto.getNum_of_guest());
        pstm.setInt(5, dto.getBed_count());
        pstm.setBoolean(6, dto.isHas_pool());
        pstm.setBoolean(7, dto.isHas_ac());
        pstm.setBoolean(8, dto.isHas_tv());
        pstm.setBoolean(9, dto.isHas_wifi());
        pstm.setBoolean(10, dto.isSmoking());
        pstm.setBoolean(11, dto.isHot_water());
        pstm.setString(12, dto.getAvailability());
        pstm.setInt(13, dto.getCategory_id());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    @Override
    public  boolean changeStatus(String roomNmber,String status){
        try {
            Connection connection = DbConnetion.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update rooms set avaliable = ?  where room_number = ?");
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,roomNmber);

            int i = preparedStatement.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public  ArrayList<RoomsDto> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM rooms");
        ArrayList<RoomsDto> c = new ArrayList<>();
        while (resultSet.next()) {
            c.add(new RoomsDto(resultSet.getInt(
                    1),resultSet.getString(2),resultSet.getDouble(3),
                    resultSet.getInt(4),resultSet.getInt(5),resultSet.getBoolean(
                    6), resultSet.getBoolean(7),resultSet.getBoolean(
                    8),resultSet.getBoolean(9),resultSet.getBoolean(
                    10),resultSet.getBoolean(11), resultSet.
                    getString(12),resultSet.getInt(13)));

        }
        System.out.println(c);
        return c;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RoomsDto dto) throws SQLException {
        return false;
    }
}
