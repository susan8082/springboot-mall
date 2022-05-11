package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByProductId(Integer productID);

}
