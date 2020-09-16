package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "dishes")
public class Dishes {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", nullable = false, updatable = false)
    private Long dishId;
    @Getter
    @Setter
    @Column(name = "dish_Name", length = 100, nullable = false, updatable = false)
    private String dishName;
    @Getter
    @Setter
    @Column(name = "calories", scale = 1, nullable = false)
    private Double calories;
    @Getter
    @Setter
    @Column(name = "price", scale = 2, nullable = false)
    private BigDecimal price;
    @Getter
    @Setter
    @Column(name = "balance", nullable = false)
    //запас, количество штук в остатке
    private Long balance;

    @Getter
    @Setter
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "dishes_order",
            joinColumns = {@JoinColumn(name = "dish_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Orders> ordersOfDishes;
}
