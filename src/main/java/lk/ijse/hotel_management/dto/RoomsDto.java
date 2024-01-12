package lk.ijse.hotel_management.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomsDto {

    private int id;
    private String room_number;
    private double room_price;
    private int num_of_guest;
    private  int bed_count;
    private  boolean has_pool;
    private boolean has_ac;
    private boolean has_tv;
    private boolean has_wifi;
    private boolean smoking;
    private boolean hot_water;
    private String availability;
    private int category_id;

}
