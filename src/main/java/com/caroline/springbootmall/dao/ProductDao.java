package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer productId);
    Product createProduct(ProductRequestDto product);
    void updateProduct(Integer productId, ProductRequestDto product);
    void deleteProductById(Integer productId);
    List<Product> getAllProducts();
}
