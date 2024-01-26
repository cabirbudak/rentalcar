package com.example.rentalcar.User;


import jakarta.persistence.*;
import lombok.*;
import com.example.rentalcar.Entity.Order.OrderStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @private Long id;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "ending")
    private LocalDateTime end;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    public Order(LocalDateTime start, LocalDateTime end, OrderStatus status, Integer totalPrice, Boolean active, Car car, User user) {
        this.start = start;
        this.end = end;
        this.status = status;
        this.totalPrice = totalPrice;
        this.active = active;
        this.car = car;
        this.user = user;
    }
}
