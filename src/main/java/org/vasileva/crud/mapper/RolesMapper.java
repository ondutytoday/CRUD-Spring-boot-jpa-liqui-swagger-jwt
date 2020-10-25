package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.RolesDto;
import org.vasileva.crud.entity.Roles;

@Mapper(componentModel = "spring")
@Component
public interface RolesMapper {

    RolesDto toRolesDto (Roles role);

    Roles toRoles (RolesDto rolesDto);
}
