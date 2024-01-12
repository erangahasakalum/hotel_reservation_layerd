package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.ReservationDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.ReservationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public  boolean save(ReservationDto reservationDto){
        try {
            Connection connection = DbConnetion.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservation values (?,?,?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2, String.valueOf(Date.valueOf(reservationDto.getCheak_in_date())));
            preparedStatement.setString(3, String.valueOf(Date.valueOf(reservationDto.getCheak_out_date())));
            preparedStatement.setString(4,"das");
            preparedStatement.setInt(5,reservationDto.getCustomer_id());
            preparedStatement.setInt(6,reservationDto.getPayment_id());

            int i = preparedStatement.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public  List<ReservationDto> getAll() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM reservation";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ReservationDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new ReservationDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)

                    )
            );
        }
        return dtoList;
    }
    @Override
    public  boolean delete(int reservationId) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, reservationId);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(ReservationDto dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<ReservationDto> searchReservation(String s) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "select * from customer where customer_nic =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, s);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ReservationDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new ReservationDto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)

                    )
            );
        }
        return dtoList;

    }

}
