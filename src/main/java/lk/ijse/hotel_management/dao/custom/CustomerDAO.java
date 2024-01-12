package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer getcustomerName(int custID);
    ArrayList<Customer> searchCustomer(String s) throws SQLException, ClassNotFoundException;


}
