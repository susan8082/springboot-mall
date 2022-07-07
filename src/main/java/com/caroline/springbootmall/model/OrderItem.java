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
    private Integer quantity;
    private Integer amount;

    public static OrderItem getOrderItem(Product product, Integer quantity){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getProductId());
        orderItem.setQuantity(quantity);
        orderItem.setAmount(product.getPrice()*quantity);
        return orderItem;
    }

}
