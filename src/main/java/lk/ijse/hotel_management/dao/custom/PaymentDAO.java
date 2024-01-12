package lk.ijse.hotel_management.dao.custom;

import lk.ijse.hotel_management.dao.CrudDAO;
import lk.ijse.hotel_management.db.DbConnetion;
import lk.ijse.hotel_management.dto.PaymentDto;

import java.sql.*;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<PaymentDto> {
    PaymentDto getPaymentByID(int id);


}
