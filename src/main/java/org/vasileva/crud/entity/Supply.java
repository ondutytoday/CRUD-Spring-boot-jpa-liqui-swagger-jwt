package org.vasileva.crud.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "supply")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supply_id", nullable = false, updatable = false)
    private Long supplyId;

    @Column(name = "date_of_supply", nullable = false)
    private Date dateOfSupply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "FK_supplier"))
    private Suppliers supplier;

    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DishesSupply> dishesSupplies;

    public Supply(Date dateOfSupply, Suppliers supplier, Set<DishesSupply> dishesSupplies) {
        this.dateOfSupply = dateOfSupply;
        this.supplier = supplier;
        this.dishesSupplies = dishesSupplies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supply supply = (Supply) o;

        return supplyId.equals(supply.supplyId);
    }

    @Override
    public int hashCode() {
        return supplyId.hashCode();
    }

    @Override
    public String toString() {
        return "supplyId=" + supplyId +
                ", dateOfSupply=" + dateOfSupply +
                ", supplier=" + supplier +
                ", dishesSupplies=" + dishesSupplies;
    }
}
