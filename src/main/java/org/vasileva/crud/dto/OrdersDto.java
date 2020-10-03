package org.vasileva.crud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Список заказов")
public class OrdersDto {

    @ApiModelProperty(notes = "Дата и время заказа", example = "2020-09-30T17:54:21.132Z", required = true)
    private Date timestamp;

    @ApiModelProperty(notes = "Сотрудник, принимающий заказ", required = true)
    private Staff staff;

    @ApiModelProperty(notes = "Метод оплаты", example = "cash", required = true)
    private PaymentMethod paymentMethod;

    @ApiModelProperty(notes = "Список блюд в заказе", required = false)
    private List<Dishes> dishesInOrder;
}
