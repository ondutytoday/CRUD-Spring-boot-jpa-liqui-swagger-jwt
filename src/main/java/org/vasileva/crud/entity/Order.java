package org.vasileva.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Getter
    @Setter
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
    @Getter
    @Setter
    @Column(name = "staff_id", nullable = false)
    @ManyToOne
    @JoinColumn(name = "personnel_number")
    private Staff staffId;
    @Getter
    @Setter
    @Column(name = "payment_method", length = 4, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

}
