package com.caroline.springbootmall.service;


import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    Product createProduct(ProductRequestDto productDto);

    void updateProduct(Integer productId, ProductRequestDto productDto);

    void deleteProductById(Integer productId);

    List<Product> getAllProducts(ProductQueryParams productQueryParams);
}
