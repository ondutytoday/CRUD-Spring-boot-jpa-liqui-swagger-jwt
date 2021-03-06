package org.vasileva.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", nullable = false)
    private Long dishId;

    @Column(name = "dish_Name", length = 100, nullable = false)
    private String dishName;

    @Column(name = "calories", scale = 1, nullable = false)
    private Double calories;

    @Column(name = "price", scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "balance", nullable = false)
    //запас, количество штук в остатке
    private Integer balance;

    @ManyToMany (mappedBy = "dishesInOrder", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Orders> ordersOfDishes;

    @OneToMany (mappedBy = "dish", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DishesSupply> dishesSupplies;

    public Dishes(String dishName, Double calories, BigDecimal price, Integer balance) {
        this.dishName = dishName;
        this.calories = calories;
        this.price = price;
        this.balance = balance;
    }

    public void setOrdersOfDishes(Set<Orders> ordersOfDishes) {
        this.ordersOfDishes.clear();
        if (ordersOfDishes != null) {
            this.ordersOfDishes.addAll(ordersOfDishes);
        }
    }

    public void setDishesSupplies(Set<DishesSupply> dishesSupplies) {
        this.dishesSupplies.clear();
        if (dishesSupplies != null) {
            this.dishesSupplies.addAll(dishesSupplies);
        }
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
