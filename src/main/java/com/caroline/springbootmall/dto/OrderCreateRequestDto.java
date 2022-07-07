package com.caroline.springbootmall.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequestDto {
    private List<BuyItem> items;
}
