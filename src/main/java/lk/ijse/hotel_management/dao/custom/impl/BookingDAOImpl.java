package lk.ijse.hotel_management.dao.custom.impl;

import lk.ijse.hotel_management.dao.custom.BookingDAO;
import lk.ijse.hotel_management.dto.ReservationDto;
import lk.ijse.hotel_management.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean save(ReservationDto dto) {
        Boolean execute=false;
        try {
            execute= CrudUtil.execute("insert into reservation (reservation_id,check_in_date," +
                            "cheack_out_date,reservation_status,special_requests,customer_id,payment_id) " +
                            "set values (?,?,?,?,?,?,?)", dto.getResevation_id(), dto.getCheak_in_date(),
                    dto.getCheak_out_date(), dto.getReservation_states(),
                    dto.getCustomer_id(), null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;

    }

    @Override
    public List<ReservationDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ReservationDto dto) throws SQLException {
        return false;
    }
}
