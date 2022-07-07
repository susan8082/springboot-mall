package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
