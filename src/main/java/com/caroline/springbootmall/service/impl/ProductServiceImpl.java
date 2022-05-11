package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.ProductDao;
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
}
