package lk.ijse.hotel_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {

    private int resevation_id;
    private String cheak_in_date;
    private String cheak_out_date;
    private String reservation_states;
    private int customer_id;
    private int payment_id;

    public Reservation(int anInt, String string, String string1, String string2, String string3, String string4) {
    }
}
