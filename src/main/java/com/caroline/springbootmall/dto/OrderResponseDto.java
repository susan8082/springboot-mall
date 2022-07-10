package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
    Integer totalCount;
    List<Order> results;
}
