package org.vasileva.crud.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.vasileva.crud.dto.UsersDto;
import org.vasileva.crud.entity.Users;

@Mapper(componentModel = "spring")
@Component
public interface UsersMapper {

    UsersDto toUsersDto (Users user);

    Users toUser (UsersDto usersDto);
}
