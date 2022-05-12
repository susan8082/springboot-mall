package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Transactional
    Integer deleteProductByProductId(Integer productID);

    List<Product> findByProductId(Integer productID);

}
