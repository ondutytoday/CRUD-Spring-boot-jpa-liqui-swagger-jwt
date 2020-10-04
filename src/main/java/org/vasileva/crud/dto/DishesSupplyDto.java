package org.vasileva.crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Вспомогательная таблица для отношения блюд и поставок и учета количества и цены блюд в поставке")
public class DishesSupplyDto {

    @ApiModelProperty(notes = "Количество определенного блюда в поставке", example = "3", required = true)
    private Integer quantity;

    @ApiModelProperty(notes = "Цена блюда в данной поставке", example = "12.99", required = true)
    private BigDecimal price;

    @ApiModelProperty(notes = "Поставка", required = true)
/*    @JsonBackReference*/
    private Supply supply;

    @ApiModelProperty(notes = "Блюдо", required = true)
/*    @JsonBackReference*/
    private Dishes dish;
}
