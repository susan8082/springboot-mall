package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    Integer deleteProductByProductId(Integer productID);

    List<Product> findByProductId(Integer productID);

    Page<Product> findAllByCategory(ProductCategory category, Pageable paging);

    Page<Product> findAllByProductNameContaining(String search, Pageable paging);

    Page<Product> findAllByCategoryAndProductNameContaining(ProductCategory category, String search, Pageable paging);

}
