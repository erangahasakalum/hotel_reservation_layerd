package lk.ijse.hotel_management.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString

public class EmployeeDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String phone;
    private String email;
}
