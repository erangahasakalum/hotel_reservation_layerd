package lk.ijse.hotel_management.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReservationTm {

    private int reservationId;
    private String customerName;
    private String mobilNumber;
    private String checkInDate;
    private String checkOutDate;
    private String states;
    private JFXButton deletebtn;
    private JFXButton updatebtn;
}
