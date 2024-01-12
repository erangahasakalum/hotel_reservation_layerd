package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.ReservationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDAO extends CrudDAO<ReservationDto> {

    ArrayList<ReservationDto> searchReservation(String s) throws SQLException;

}
