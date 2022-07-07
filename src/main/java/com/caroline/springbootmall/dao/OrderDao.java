package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;

import java.util.List;

public interface OrderDao {

    Order createOrder(Integer userId, OrderCreateRequestDto dto);

    List<Order> getUserOrders(Integer userId);
}
