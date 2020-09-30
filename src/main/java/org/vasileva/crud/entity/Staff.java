package org.vasileva.crud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staff")
@ApiModel(description = "Список сотрудников ресторана")
public class Staff {

    @Id
    @SequenceGenerator(name = "personnelNumberSequence", sequenceName = "STAFF_SEQUENCE", allocationSize = 1, initialValue = 2000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personnelNumberSequence")
    @Column(name = "personnel_number")
    @ApiModelProperty(notes = "Генерируемый автоматически уникальный идентификационный номер сотрудника",
            example = "1", required = true)
    private Long personnelNumber;

    @Column(name = "surname", length = 100)
    @ApiModelProperty(notes = "Фамилия",
            example = "Иванов", required = true)
    private String surname;

    @Column(name = "name", length = 50, nullable = false)
    @ApiModelProperty(notes = "Имя",
            example = "Василий", required = true)
    private String name;

    @Column(name = "patronymic", length = 100, nullable = true)
    @ApiModelProperty(notes = "Отчество",
            example = "Петрович", required = true)
    private String patronymic;

    @Column(name = "gender", length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Пол",
            example = "female", required = true)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    @ApiModelProperty(notes = "Дата рождения",
            example = "10.10.1990", required = true)
    private Date dateOfBirth;

    @Column(name = "passport", nullable = false)
    @ApiModelProperty(notes = "серия и номер паспорта, кем выдан и т.д.",
            example = "3652 456789 выдан 12.12.2016 Ленинским ОВД г. Самары", required = true)
    private String passport;

    @Column(name = "home_address", nullable = false)
    @ApiModelProperty(notes = "Домашний адрес",
            example = "443077, Самара, ул. Садовая 23, кв. 31", required = true)
    private String homeAddress;

    @Column(name = "position", length = 100, nullable = false)
    @ApiModelProperty(notes = "Должность",
            example = "кассир", required = true)
    private String position;

    @Column(name = "salary", nullable = false)
    @ApiModelProperty(notes = "Зарплата",
            example = "1000", required = true)
    private BigDecimal salary;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    @ApiModelProperty(notes = "Список заказов, принятых данным сотрудником", required = false)
    private Set<Orders> orders;

    public Staff(String surname, String name, String patronymic,
                 Gender gender, Date dateOfBirth, String passport,
                 String homeAddress, String position, BigDecimal salary) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
        this.homeAddress = homeAddress;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" + personnelNumber +
                ", " + surname + ' ' +
                name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return personnelNumber.equals(staff.personnelNumber);
    }

    @Override
    public int hashCode() {
        return personnelNumber.hashCode();
    }
}
