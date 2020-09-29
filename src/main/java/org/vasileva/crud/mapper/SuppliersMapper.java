package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.SuppliersDto;
import org.vasileva.crud.entity.Suppliers;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SuppliersMapper {

    SuppliersDto toSuppliersDto(Suppliers supplier);

    Suppliers toSuppliers (SuppliersDto supplierDto);

    List<SuppliersDto> toListSuppliersDto(List<Suppliers> suppliers);
}
