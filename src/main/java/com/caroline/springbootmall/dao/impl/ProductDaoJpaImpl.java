package com.caroline.springbootmall.dao.impl;

import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dao.repository.ProductRepository;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoJpaImpl implements ProductDao {

    @Autowired
    ProductRepository productRepo;

    @Override
    public Product getProductById(Integer productId) {
        List<Product> products = productRepo.findByProductId(productId);
        if (products.size()>0){
            return products.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Product createProduct(ProductRequestDto productDto) {
        return productRepo.save(ProductRequestDto.convertToProduct(productDto));
    }
}
