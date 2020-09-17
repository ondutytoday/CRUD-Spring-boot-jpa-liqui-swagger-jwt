package org.vasileva.crud.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "supply")
public class Supply {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supply_id", nullable = false, updatable = false)
    private Long supplyId;
    @Getter
    @Setter
    @Column(name = "date_of_supply", nullable = false)
    private Timestamp dateOfSupply;
    @Getter
    @Setter
    @Column(name = "supplier_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier;
    @Getter
    @Setter
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL)
    private Set<DishesSupply> dishesSupplies;

    public Supply(Timestamp dateOfSupply, Suppliers supplier, Set<DishesSupply> dishesSupplies) {
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
