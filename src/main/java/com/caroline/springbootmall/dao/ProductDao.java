package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer productId);
    Product createProduct(ProductRequestDto product);
    void updateProduct(Integer productId, ProductRequestDto product);
    void deleteProductById(Integer productId);
    Page<Product> getAllProducts(ProductQueryParams productQueryParams);
}
