package org.vasileva.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.entity.Suppliers;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyDto {

    private Timestamp dateOfSupply;

    private Suppliers supplier;

    private Set<DishesSupply> dishesSupplies;
}
