package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Product createProduct(ProductRequestDto productDto) {
        return productDao.createProduct(productDto);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequestDto productDto) {
        productDao.updateProduct(productId, productDto);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);

    }

}
