package org.vasileva.crud.dto;

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
public class StaffDto {

    private String surname;

    private String name;

    private String patronymic;

    private Gender gender;

    private Date dateOfBirth;

    private String passport;

    private String homeAddress;

    private String position;

    private BigDecimal salary;

    private Set<Orders> orders;
}
