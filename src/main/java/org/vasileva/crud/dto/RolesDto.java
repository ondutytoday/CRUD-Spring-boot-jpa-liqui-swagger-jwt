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
@ApiModel(description = "Уровень доступа пользователя")
public class RolesDto {

    @ApiModelProperty(notes = "роль", example = "USER", required = true)
    private String roleName;
}
