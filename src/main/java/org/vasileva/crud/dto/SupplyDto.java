package org.vasileva.crud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.entity.Suppliers;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Список поставок")
public class SupplyDto {

    @ApiModelProperty(notes = "Дата поставки", example = "2020-09-30T17:54:21.132Z", required = true)
    private Date dateOfSupply;

    @ApiModelProperty(notes = "Поставщик", required = true)
    private Suppliers supplier;

    @ApiModelProperty(notes = "Список отношений блюдо-поставка", required = false)
    private Set<DishesSupply> dishesSupplies;
}
