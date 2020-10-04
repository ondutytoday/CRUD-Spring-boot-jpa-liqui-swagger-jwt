package org.vasileva.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id", nullable = false, updatable = false)
    private Long supplierId;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "supplier_address", nullable = false)
    private String supplierAddress;

    @Column(name = "inn", nullable = false)
    private Integer inn;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "information", nullable = true)
    private String information;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Supply> supplies;

    public Suppliers(String supplierName, String supplierAddress,
                     Integer inn, String phoneNumber, String email, String information) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.inn = inn;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.information = information;
    }

    @Override
    public String toString() {
        return "supplierId=" + supplierId +
                ", supplierName='" + supplierName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suppliers suppliers = (Suppliers) o;

        return supplierId.equals(suppliers.supplierId);
    }

    @Override
    public int hashCode() {
        return supplierId.hashCode();
    }
}
