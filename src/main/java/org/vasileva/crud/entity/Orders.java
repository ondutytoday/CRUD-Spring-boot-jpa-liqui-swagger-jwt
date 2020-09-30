package org.vasileva.crud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@ApiModel(description = "Список заказов")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "Генерируемый автоматически уникальный идентификационный номер заказа",
            example = "1", required = true)
    private Long orderId;

    @Column(name = "timestamp", nullable = false)
    @ApiModelProperty(notes = "Дата и время заказа",
            example = "2020-09-30T17:54:21.132Z", required = true)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel_number", foreignKey = @ForeignKey(name = "FK_STAFF"))
    @ApiModelProperty(notes = "Сотрудник, принимающий заказ", required = true)
    private Staff staff;

    @Column(name = "payment_method", length = 4, nullable = false)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "Метод оплаты",
            example = "cash", required = true)
    private PaymentMethod paymentMethod;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_order",
            joinColumns = @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order")),
            inverseJoinColumns = @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "fk_dish")))
    @ApiModelProperty(notes = "Список блюд в заказе", required = false)
    private List<Dishes> dishesInOrder;

    public Orders(Date timestamp, Staff staff, PaymentMethod paymentMethod, List<Dishes> dishesInOrder) {
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
