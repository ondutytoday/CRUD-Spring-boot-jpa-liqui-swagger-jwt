package org.vasileva.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.entity.Supply;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishesSupplyDto {
    private Integer quantity;

    private BigDecimal price;

    private Supply supply;

    private Dishes dish;
}
