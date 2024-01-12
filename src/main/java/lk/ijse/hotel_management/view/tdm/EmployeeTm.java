package lk.ijse.hotel_management.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class EmployeeTm {
    private int id;
    private String name;
    private String address;
    private String nic;
    private String phone;
    private String email;
    private JFXButton updatebtn;
    private JFXButton deletebtn;
}
