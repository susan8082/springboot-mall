package com.caroline.springbootmall.service;

import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, OrderCreateRequestDto createOrderRequestDto);

    List<Order> getUserOrders(Integer userId);

    Order getOrderById(Integer orderId);
}
