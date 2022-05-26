package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import org.springframework.data.domain.Page;

public interface CompanyDao {
    Company getCompanyByCompanyNum(String companyNumber);
    Company createCompany(CompanyRequestDto company);
    void updateCompany(String companyNum, CompanyRequestDto companyRequestDto);
    void updateCompany(String companyNum, CompanySyncResponseDto companyRequestDto);
//    void deleteProductById(Integer productId);
//    Page<Product> getAllProducts(ProductQueryParams productQueryParams);
}
