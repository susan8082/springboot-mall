package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
    List<Order> findByUserIdOrderByCreatedDateDesc(Integer userId);
    List<Order> findByOrderId(Integer orderId);
}
