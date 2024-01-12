package lk.ijse.hotel_management.view.tdm;

import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor

public class RoomsTm extends RoomsDetailsTm {

    private String room_number;
    private String categoryName;
    private  int bed_count;
    private double room_price;
    private String states;


}
