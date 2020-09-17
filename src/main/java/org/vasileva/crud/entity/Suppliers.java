package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table (name = "suppliers")
public class Suppliers {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id", nullable = false, updatable = false)
    private Long supplierId;

    @Getter
    @Setter
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;
    @Getter
    @Setter
    @Column(name = "supplier_address", nullable = false)
    private String supplierAddress;
    @Getter
    @Setter
    @Column(name = "inn", nullable = false)
    private Integer inn;
    @Getter
    @Setter
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;
    @Getter
    @Setter
    @Column(name = "information", nullable = true)
    private String information;
    @Getter
    @Setter
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
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
        StringBuilder sb = new StringBuilder();
        sb.append("[" + "supplierId=").append(supplierId).append(", supplierName='").append(supplierName)
                .append(", supplierAddress='").append(supplierAddress).append(", inn=").append(inn)
                .append(", phoneNumber='").append(phoneNumber).append(", email='").append(email);
        if (information != null) sb.append(", information='").append(information);
        sb.append("]");
        return sb.toString();
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
