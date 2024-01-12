package lk.ijse.hotel_management.entity;

import lombok.*;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
@ToString

public class Customer {
    private int customer_id;
    private String customer_firstName;
    private String customer_lastName;
    private String customer_address;
    private String customer_nic;
    private String customer_phone;
    private String customer_email;

}
