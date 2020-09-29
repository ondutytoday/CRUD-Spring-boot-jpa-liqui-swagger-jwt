package org.vasileva.crud.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.DishesDto;
import org.vasileva.crud.entity.Dishes;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DishesMapper {

    DishesDto toDishesDto(Dishes dish);

    Dishes toDishes (DishesDto dishesDto);

    List<DishesDto> toListDishesDto(List<Dishes> dishes);
}
