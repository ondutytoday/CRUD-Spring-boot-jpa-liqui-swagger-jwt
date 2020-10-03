package org.vasileva.crud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vasileva.crud.entity.Gender;
import org.vasileva.crud.entity.Orders;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Список сотрудников ресторана")
public class StaffDto {

    @ApiModelProperty(notes = "Фамилия", example = "Иванов", required = true)
    private String surname;

    @ApiModelProperty(notes = "Имя", example = "Василий", required = true)
    private String name;

    @ApiModelProperty(notes = "Отчество", example = "Петрович", required = false)
    private String patronymic;

    @ApiModelProperty(notes = "Пол", example = "female", required = true)
    private Gender gender;

    @ApiModelProperty(notes = "Дата рождения", example = "10.10.1990", required = true)
    private Date dateOfBirth;

    @ApiModelProperty(notes = "серия и номер паспорта, кем выдан и т.д.", example = "3652 456789 выдан 12.12.2016 Ленинским ОВД г. Самары", required = true)
    private String passport;

    @ApiModelProperty(notes = "Домашний адрес", example = "443077, Самара, ул. Садовая 23, кв. 31", required = true)
    private String homeAddress;

    @ApiModelProperty(notes = "Должность", example = "кассир", required = true)
    private String position;

    @ApiModelProperty(notes = "Зарплата", example = "1000", required = true)
    private BigDecimal salary;

    @ApiModelProperty(notes = "Список заказов, принятых данным сотрудником", required = false)
    private Set<Orders> orders;
}
