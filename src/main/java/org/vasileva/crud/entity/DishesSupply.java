package org.vasileva.crud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Вспомогательная таблица для отношения блюд и поставок и учета количества и цены блюд в поставке")
public class DishesSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dishes_supply_id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "Генерируемый автоматически уникальный идентификационный номер отношения блюдо-поставка",
            example = "1", required = true)
    private Long id;

    @Column(name = "quantity", nullable = false)
    @ApiModelProperty(notes = "Количество определенного блюда в поставке",
            example = "3", required = true)
    private Integer quantity;

    @Column(name = "price", scale = 2, nullable = false)
    @ApiModelProperty(notes = "Цена блюда в данной поставке",
            example = "12.99", required = true)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id", foreignKey = @ForeignKey(name = "FK_supply"))
    @ApiModelProperty(notes = "Поставка", required = true)
    private Supply supply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "FK_dish"))
    @ApiModelProperty(notes = "Блюдо", required = true)
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
