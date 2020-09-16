package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personnel_number")
    private Long personnelNumber;

    @Getter
    @Setter
    @Column(name = "surname", length = 100)
    private String surname;
    @Getter
    @Setter
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Getter
    @Setter
    @Column(name = "patronymic", length = 100, nullable = true)
    private String patronymic;
    @Getter
    @Setter
    @Column(name = "gender", length = 20, nullable = false)
    private String gender;
    @Getter
    @Setter
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;
    @Getter
    @Setter
    @Column(name = "passport", nullable = false)
    private String passport;
    @Getter
    @Setter
    @Column(name = "home_address", nullable = false)
    private String homeAddress;
    @Getter
    @Setter
    @Column(name = "position", length = 100, nullable = false)
    private String position;
    @Getter
    @Setter
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    public Staff(String surname, String name, String patronymic,
                 String gender, Date dateOfBirth, String passport,
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
        return "Staff{" +
                "personnelNumber=" + personnelNumber +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passport='" + passport + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
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
