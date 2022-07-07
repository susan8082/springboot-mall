package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(Integer orderId);

}
