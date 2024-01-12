package lk.ijse.hotel_management.dao;

import lk.ijse.hotel_management.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
}
