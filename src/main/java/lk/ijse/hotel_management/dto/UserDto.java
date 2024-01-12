package lk.ijse.hotel_management.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private int id;
    private String userName;
    private String password;

    public UserDto(String userName) {

    }
}
