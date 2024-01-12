package lk.ijse.hotel_management.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PoolDto {
    private int pool_id;
    private String pool_name;
    private String pool_description;
    private  int capacity;
    private double price;
    private String is_available;
    private String pool_opening_hours;

}
