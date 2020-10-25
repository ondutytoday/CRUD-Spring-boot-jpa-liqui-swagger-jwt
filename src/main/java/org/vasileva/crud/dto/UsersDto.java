package org.vasileva.crud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Зарегистрированные пользователи системы")
public class UsersDto {

    @ApiModelProperty(notes = "имя пользователя", example = "user01", required = true)
    private String username;

    @ApiModelProperty(notes = "пароль", example = "123", required = true)
    private String password;
}
