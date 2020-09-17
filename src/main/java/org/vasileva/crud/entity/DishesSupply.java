package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Table(name = "dishes_supply")
public class DishesSupply {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dishes_supply_id", nullable = false, updatable = false)
    private Long id;
    @Getter
    @Setter
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Getter
    @Setter
    @Column(name = "price", scale = 2, nullable = false)
    private BigDecimal price;
    @Getter
    @Setter
    @Column(name = "supply_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id")
    private Supply supply;
    @Getter
    @Setter
    @Column(name = "dish_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dishes dish;

    public DishesSupply(Integer quantity, BigDecimal price, Supply supply, Dishes dish) {
        this.quantity = quantity;
        this.price = price;
        this.supply = supply;
        this.dish = dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishesSupply that = (DishesSupply) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return ", dish=" + dish +
                ", quantity=" + quantity +
                ", price=" + price;
    }
}
