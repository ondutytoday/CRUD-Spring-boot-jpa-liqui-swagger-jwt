package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.DishesSupplyDto;
import org.vasileva.crud.entity.DishesSupply;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DishesSupplyMapper {

    DishesSupplyDto toDishesSupplyDto(DishesSupply dishSupply);

    DishesSupply toDishesSupply (DishesSupplyDto dishSupplyDto);

    List<DishesSupplyDto> toListDishesSupplyDto (List<DishesSupply> dishesSupply);
}
