package org.vasileva.crud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Список поставщиков")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "Генерируемый автоматически уникальный идентификационный номер поставщика",
            example = "1", required = true)
    private Long supplierId;

    @Column(name = "supplier_name", nullable = false)
    @ApiModelProperty(notes = "Название фирмы поставщика",
            example = "ООО \"Моя Оборона\"", required = true)
    private String supplierName;

    @Column(name = "supplier_address", nullable = false)
    @ApiModelProperty(notes = "Адрес поставщика",
            example = "443077, Самара, ул. Садовая 23, офис 31", required = true)
    private String supplierAddress;

    @Column(name = "inn", nullable = false)
    @ApiModelProperty(notes = "ИНН",
            example = "365412789", required = true)
    private Integer inn;

    @Column(name = "phone_number", nullable = false)
    @ApiModelProperty(notes = "Номер телефона",
            example = "8-800-335-35-55", required = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    @ApiModelProperty(notes = "адрес электронной почты",
            example = "email@email.com", required = true)
    private String email;

    @Column(name = "information", nullable = true)
    @ApiModelProperty(notes = "Дополнительная информация",
            example = "Не дает откаты", required = false)
    private String information;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @ApiModelProperty(notes = "Список поставок, которые произвел поставщик", required = false)
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
