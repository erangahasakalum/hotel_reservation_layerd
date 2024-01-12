package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<EmployeeDto> {

    ArrayList<EmployeeDto> searchEmployee(String s) throws SQLException;
}
