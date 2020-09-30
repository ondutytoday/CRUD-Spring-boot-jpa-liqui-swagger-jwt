package org.vasileva.crud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dishes")
@ApiModel(description = "Список блюд ресторана")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", nullable = false)
    @ApiModelProperty(notes = "Генерируемый автоматически уникальный идентификационный номер блюда",
    example = "1", required = true)
    private Long dishId;

    @Column(name = "dish_Name", length = 100, nullable = false)
    @ApiModelProperty(notes = "Название блюда",
            example = "Биг Мак", required = true)
    private String dishName;

    @Column(name = "calories", scale = 1, nullable = false)
    @ApiModelProperty(notes = "Энергетическая ценность в ккал",
            example = "201.25", required = true)
    private Double calories;

    @Column(name = "price", scale = 2, nullable = false)
    @ApiModelProperty(notes = "Цена за 1 штуку",
            example = "15.32", required = true)
    private BigDecimal price;

    @Column(name = "balance", nullable = false)
    @ApiModelProperty(notes = "Количество штук в остатке (на складе)",
            example = "250", required = true)
    //запас, количество штук в остатке
    private Integer balance;

    @ManyToMany (mappedBy = "dishesInOrder")
    @ApiModelProperty(notes = "Список заказов, в котором присутствует данное блюдо",
            required = false)
    private Set<Orders> ordersOfDishes;

    @OneToMany (mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    @ApiModelProperty(notes = "Список списка поставок и блюд в этой поставке",
            required = false)
    private Set<DishesSupply> dishesSupplies;

    public Dishes(String dishName, Double calories, BigDecimal price, Integer balance) {
        this.dishName = dishName;
        this.calories = calories;
        this.price = price;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return dishName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dishes dishes = (Dishes) o;

        return dishId.equals(dishes.dishId);
    }

    @Override
    public int hashCode() {
        return dishId.hashCode();
    }
}
