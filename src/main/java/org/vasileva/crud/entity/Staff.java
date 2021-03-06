package org.vasileva.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @SequenceGenerator(name = "personnelNumberSequence", sequenceName = "STAFF_SEQUENCE", allocationSize = 1, initialValue = 2000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personnelNumberSequence")
    @Column(name = "personnel_number")
    private Long personnelNumber;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "patronymic", length = 100, nullable = true)
    private String patronymic;

    @Column(name = "gender", length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Column(name = "passport", nullable = false)
    private String passport;

    @Column(name = "home_address", nullable = false)
    private String homeAddress;

    @Column(name = "position", length = 100, nullable = false)
    private String position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
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

    public void setOrders(Set<Orders> orders) {
        this.orders.clear();
        if (orders != null) {
            this.orders.addAll(orders);
        }
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
