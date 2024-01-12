package lk.ijse.hotel_management.bo.custom;

import lk.ijse.hotel_management.bo.SuperBO;
import lk.ijse.hotel_management.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(int id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto) throws SQLException;
    CustomerDto getCustomerName(int custID);
    ArrayList<CustomerDto> searchCustomer(String s) throws SQLException, ClassNotFoundException;
}
