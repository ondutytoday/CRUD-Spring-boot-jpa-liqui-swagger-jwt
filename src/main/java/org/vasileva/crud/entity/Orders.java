package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long orderId;
    @Getter
    @Setter
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel_number", foreignKey = @ForeignKey(name = "FK_STAFF"))
    private Staff staff;

    @Getter
    @Setter
    @Column(name = "payment_method", length = 4, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Getter
    @Setter
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_order",
            joinColumns = @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order")),
            inverseJoinColumns = @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "fk_dish")))
    private List<Dishes> dishesInOrder;

    public Orders(Timestamp timestamp, Staff staff, PaymentMethod paymentMethod, List<Dishes> dishesInOrder) {
        this.timestamp = timestamp;
        this.staff = staff;
        this.paymentMethod = paymentMethod;
        this.dishesInOrder = dishesInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        return orderId.equals(orders.orderId);
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }

    @Override
    public String toString() {
        return "orderId=" + orderId +
                ", timestamp=" + timestamp +
                ", staff=" + staff +
                ", paymentMethod=" + paymentMethod +
                ", dishesInOrder=" + dishesInOrder;
    }
}
