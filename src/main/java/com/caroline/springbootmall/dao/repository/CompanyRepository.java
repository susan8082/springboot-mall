package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

//    @Transactional
//    Integer deleteProductByProductId(Integer productID);

    Company findByCompanyNumber(String companyNumber);

//    Page<Product> findAllByCategory(ProductCategory category, Pageable paging);
//
}
