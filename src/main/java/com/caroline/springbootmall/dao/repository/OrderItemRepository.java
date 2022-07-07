package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
