package com.caroline.springbootmall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer totalAmount;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer userId;
    @Transient
    private List<OrderItem> orderItems;
}
