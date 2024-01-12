package lk.ijse.hotel_management.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RoomsDetailsTm {
    private String room_number;
    private double room_price;
    private String room_category;
    private String pool;
    private String ac;
    private String tv;
    private String wifi;
    private String smoking;
    private String hot_water;
    private int bed_count;
    private JFXButton deletebtn;
    private JFXButton update;


}

