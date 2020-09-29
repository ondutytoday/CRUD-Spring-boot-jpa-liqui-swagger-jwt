package org.vasileva.crud.dto;

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
public class DishesDto {

    private String dishName;

    private Double calories;

    private BigDecimal price;

    private Integer balance;

    private Set<Orders> ordersOfDishes;

    private Set<DishesSupply> dishesSupplies;
}
