package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.SupplyDto;
import org.vasileva.crud.entity.Supply;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SupplyMapper {

    SupplyDto toSupplyDto(Supply supply);

    Supply toSupply (SupplyDto supplyDto);

    List<SupplyDto> toListSupplyDto(List<Supply> supplies);
}
