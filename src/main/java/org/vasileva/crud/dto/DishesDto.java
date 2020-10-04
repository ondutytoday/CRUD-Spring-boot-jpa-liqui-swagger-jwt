package org.vasileva.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.entity.Orders;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Список блюд ресторана")
public class DishesDto {

    @ApiModelProperty(notes = "Название блюда", example = "Биг Мак", required = true)
    private String dishName;

    @ApiModelProperty(notes = "Энергетическая ценность в ккал", example = "201.25", required = true)
    private Double calories;

    @ApiModelProperty(notes = "Цена за 1 штуку", example = "15.32", required = true)
    private BigDecimal price;

    @ApiModelProperty(notes = "Количество штук в остатке (на складе)", example = "250", required = true)
    private Integer balance;

    @ApiModelProperty(notes = "Список заказов, в котором присутствует данное блюдо", required = false)
/*    @JsonIgnoreProperties("ordersOfDishes")*/
    @JsonIgnore
    private Set<Orders> ordersOfDishes;

    @ApiModelProperty(notes = "Список списка поставок и блюд в этой поставке", required = false)
/*    @JsonIgnoreProperties("dishesSupplies")*/
/*    @JsonManagedReference*/
    @JsonIgnore
    private Set<DishesSupply> dishesSupplies;
}
