package lk.ijse.hotel_management.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class RoomCategoryTm {
    private int id;
    private  String name;
    private  String description;
    private JFXButton deletebtn;


}
