package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, OrderCreateRequestDto dto, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItems);
    List<Order> getUserOrders(Integer userId);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
