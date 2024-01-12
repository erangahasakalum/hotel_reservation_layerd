package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.EmployeeDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public  boolean save(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO employee(id,firstName,lastName," +
                "address,nic,phone,email) values (?,?,?,?,?,?,?)");

        pstm.setInt(1, dto.getId());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getNic());
        pstm.setString(6, dto.getPhone());
        pstm.setString(7, dto.getEmail());


        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }
    @Override
    public  List<EmployeeDto> getAll() throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new EmployeeDto(
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
    @Override
    public  boolean delete(int id) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, id);

        return pstm.executeUpdate() > 0;
    }
    @Override
    public  boolean update(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "UPDATE employee SET firstName = ? , " +
                "lastName = ? ,address = ? ,nic = ? ,phone = ? ,email = ?" +
                " WHERE id =?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1,dto.getId());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getPhone());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getNic());
        pstm.setString(7, dto.getAddress());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }
    @Override
    public ArrayList<EmployeeDto> searchEmployee(String s) throws SQLException {
        Connection connection = DbConnetion.getInstance().getConnection();

        String sql = "select * from employee where nic =?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, s);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new EmployeeDto(
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
