package lk.ijse.hotel_management.bo.custom.impl;

import lk.ijse.hotel_management.bo.custom.CustomerBO;
import lk.ijse.hotel_management.dao.custom.CustomerDAO;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCustomer_id(),dto.getCustomer_address(),dto.getCustomer_nic(),dto.getCustomer_phone(),dto.getCustomer_email(),dto.getCustomer_firstName(),dto.getCustomer_lastName()));
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
      List<Customer> customers = customerDAO.getAll();
      ArrayList<CustomerDto> getAll = new ArrayList<>();

      for(Customer customer : customers){
          getAll.add(new CustomerDto(customer.getCustomer_id(),customer.getCustomer_address(),
                  customer.getCustomer_email(),customer.getCustomer_firstName(),
                  customer.getCustomer_lastName(),customer.getCustomer_nic(),
                  customer.getCustomer_phone()));
        }
      return getAll;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return true;
    }

    @Override
    public CustomerDto getCustomerName(int custID) {
        Customer customer = customerDAO.getcustomerName(custID);
        CustomerDto customerDto = new CustomerDto();

        return null;
    }

    @Override
    public ArrayList<CustomerDto> searchCustomer(String s) throws SQLException, ClassNotFoundException {
       ArrayList<Customer> arrayList = customerDAO.searchCustomer(s);

        ArrayList<CustomerDto> searchCustomer = new ArrayList<>();

        for(Customer customer:arrayList){
            searchCustomer.add(new CustomerDto(customer.getCustomer_id(),customer.getCustomer_address(),
                    customer.getCustomer_email(),customer.getCustomer_firstName(),
                    customer.getCustomer_lastName(),customer.getCustomer_nic(),
                    customer.getCustomer_phone()));
        }
        return searchCustomer;

    }
}
