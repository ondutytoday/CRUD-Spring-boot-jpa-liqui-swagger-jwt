package org.vasileva.crud.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vasileva.crud.dto.DishesDto;
import org.vasileva.crud.entity.Dishes;

import java.util.List;

@Mapper
public interface DishesMapper {

    DishesDto toDishesDto(Dishes dish);

    Dishes toDishes (DishesDto dishesDto);

    List<DishesDto> toListDishesDto(List<Dishes> dishes);
}
