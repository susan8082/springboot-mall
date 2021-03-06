package com.caroline.springbootmall.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    @Transient
    private String productName;
    @Transient
    private String imageUrl;
    private Integer quantity;
    private Integer amount;
}
