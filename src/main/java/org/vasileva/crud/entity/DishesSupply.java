package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dishes_supply")
public class DishesSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dishes_supply_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id", foreignKey = @ForeignKey(name = "FK_supply"))
    private Supply supply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "FK_dish"))
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
