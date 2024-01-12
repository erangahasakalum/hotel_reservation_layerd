package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.SqlUtil;
import lk.ijse.hotel_management.dao.custom.CustomerDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer getcustomerName(int custID){
        Customer customer = new Customer();
        try {
            ResultSet resultSet = SqlUtil.test("select * from customer where customer_id = ?",custID);
            while (resultSet.next()){
                customer.setCustomer_id(resultSet.getInt(1));
                customer.setCustomer_firstName(resultSet.getString(2));
                customer.setCustomer_lastName(resultSet.getString(3));
                customer.setCustomer_address(resultSet.getString(4));
                customer.setCustomer_nic(resultSet.getString(5));
                customer.setCustomer_phone(resultSet.getString(6));
                customer.setCustomer_email(resultSet.getString(7));
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customer;

    }
    @Override
    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.test("INSERT INTO customer(customer_id,customer_firstName,customer_lastName," +
                "customer_address,customer_nic,customer_phone,customer_email) values (?,?,?,?,?,?,?)",entity.getCustomer_id(),
                entity.getCustomer_firstName(),entity.getCustomer_lastName(),entity.getCustomer_address(),entity.getCustomer_nic(),
                entity.getCustomer_phone(),entity.getCustomer_email());
    }

    @Override
    public  List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.test("SELECT * FROM customer");
        ArrayList<Customer> getAll = new ArrayList<>();

        while (resultSet.next()) {
            getAll.add(
                    new Customer(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)

                    )
            );
        }
        return getAll;
    }
    @Override
    public  boolean delete(int id) throws SQLException, ClassNotFoundException {
        return SqlUtil.test("DELETE FROM customer WHERE customer_id = ?",id);

    }

    @Override
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SqlUtil.test("UPDATE customer SET customer_firstName = ? , " +
                "customer_lastName = ? , customer_address = ? , customer_nic = ? ,customer_phone = ? ," +
                "customer_email = ?" + " WHERE customer_id =?",customer.getCustomer_firstName(),
                customer.getCustomer_lastName(),customer.getCustomer_address(),customer.getCustomer_nic(),
                customer.getCustomer_phone(),customer.getCustomer_email(),customer.getCustomer_id());

    }

    @Override
    public ArrayList<Customer> searchCustomer(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.test("select * from customer where customer_nic =?",s);

        ArrayList<Customer> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new Customer(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)

                    )
            );
        }
        return dtoList;

    }

}
