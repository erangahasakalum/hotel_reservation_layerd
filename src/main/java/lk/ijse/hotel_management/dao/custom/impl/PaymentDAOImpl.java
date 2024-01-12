package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.PaymentDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.PaymentDto;

import java.sql.*;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public  PaymentDto getPaymentByID(int id){
        PaymentDto paymentDto = new PaymentDto();
        try {
            Connection connection = DbConnetion.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select * from payment where payment_id = ?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentDto.setPayment_id(resultSet.getInt(1));
                paymentDto.setPayment_amount(resultSet.getDouble(2));
                paymentDto.setPayment_method(resultSet.getString(3));
                paymentDto.setPayment_time(resultSet.getString(4));
                paymentDto.setPayment_date(resultSet.getString(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return paymentDto;
    }
    @Override
    public  boolean save(PaymentDto paymentDto){
        try {
            Connection connection = DbConnetion.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into payment values(?,?,?,?,?);");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2, String.valueOf(paymentDto.getPayment_amount()));
            preparedStatement.setString(3,paymentDto.getPayment_method());
            preparedStatement.setString(4, "ads");
            preparedStatement.setString(5, String.valueOf(Date.valueOf(paymentDto.getPayment_date())));
            int i = preparedStatement.executeUpdate();
            return i>0 ;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public  ArrayList<PaymentDto> getAll(){
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        try {
            Connection connection = DbConnetion.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from payment");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PaymentDto paymentDto = new PaymentDto(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                paymentDtos.add(paymentDto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return paymentDtos;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(PaymentDto dto) throws SQLException {
        return false;
    }
}
