package lk.ijse.hotel_management.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String userName;
    private String password;

    public User(String userName) {

    }
}
