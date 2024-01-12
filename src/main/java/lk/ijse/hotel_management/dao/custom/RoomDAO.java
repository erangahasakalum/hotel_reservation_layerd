package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.RoomsDto;
import lk.ijse.hotel_management.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<RoomsDto> {

    boolean changeStatus(String roomNmber, String status);


}
