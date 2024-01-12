package lk.ijse.hotel_management.entity;

import lombok.*;

@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {
    private int payment_id;
    private double payment_amount;
    private String payment_method;
    private String payment_time;
    private String payment_date;


}
