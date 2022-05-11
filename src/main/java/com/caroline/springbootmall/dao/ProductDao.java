package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer productId);
}
