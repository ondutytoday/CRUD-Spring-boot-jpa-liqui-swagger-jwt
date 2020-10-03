package org.vasileva.crud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.Supply;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Список поставщиков")
public class SuppliersDto {

    @ApiModelProperty(notes = "Название фирмы поставщика", example = "ООО \"Моя Оборона\"", required = true)
    private String supplierName;

    @ApiModelProperty(notes = "Адрес поставщика", example = "443077, Самара, ул. Садовая 23, офис 31", required = true)
    private String supplierAddress;

    @ApiModelProperty(notes = "ИНН", example = "365412789", required = true)
    private Integer inn;

    @ApiModelProperty(notes = "Номер телефона", example = "8-800-335-35-55", required = true)
    private String phoneNumber;

    @ApiModelProperty(notes = "адрес электронной почты", example = "email@email.com", required = true)
    private String email;

    @ApiModelProperty(notes = "Дополнительная информация", example = "Не дает откаты", required = false)
    private String information;

    @ApiModelProperty(notes = "Список поставок, которые произвел поставщик", required = false)
    private Set<Supply> supplies;
}
