package org.vasileva.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.entity.PaymentMethod;
import org.vasileva.crud.entity.Staff;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {

    private Date timestamp;

    private Staff staff;

    private PaymentMethod paymentMethod;

    private List<Dishes> dishesInOrder;
}
