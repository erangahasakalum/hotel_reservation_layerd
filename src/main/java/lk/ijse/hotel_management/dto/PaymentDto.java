package lk.ijse.hotel_management.dto;

import lombok.*;

@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDto {
    private int payment_id;
    private double payment_amount;
    private String payment_method;
    private String payment_time;
    private String payment_date;


}
