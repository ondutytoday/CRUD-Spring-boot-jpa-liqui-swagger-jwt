package org.vasileva.crud.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vasileva.crud.dto.DishesDto;
import org.vasileva.crud.entity.Dishes;

@Mapper
public interface DishesMapper {

    DishesMapper DISHES_MAPPER = Mappers.getMapper(DishesMapper.class);

    DishesDto toDishesDto(Dishes dish);

    Dishes toDishes (DishesDto dishesDto);
}
