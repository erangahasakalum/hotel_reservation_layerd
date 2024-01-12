package lk.ijse.hotel_management.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTm {

    private int customer_id;
    private String customer_Name;
    private String customer_address;
    private String customer_nic;
    private String customer_phone;
    private String customer_email;
    private JFXButton updatebtn;
    private JFXButton deletebtn;


}

