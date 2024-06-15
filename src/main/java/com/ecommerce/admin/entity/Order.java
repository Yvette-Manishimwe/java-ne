package com.ecommerce.admin.entity;

import com.ecommerce.admin.entity.enums.OrderStatus;
import javax.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private String orderDescription;
    private Date date;
//after something
    private Long amount;
    private String address;
    private String payment;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Long discount;

    private Long totalAmount;
    private int trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems;





}
