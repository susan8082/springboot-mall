package com.caroline.springbootmall.service;


import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import org.springframework.data.domain.Page;

public interface CompanyService {

    Company getCompanyByCompanyNum(String companyNum);

    Company createCompany(CompanyRequestDto companyRequestDto);

    void updateCompany(String companyNum, CompanyRequestDto companyRequestDto);

    void updateCompany(String companyNum, CompanySyncResponseDto companySyncRequestDto);
//
//    void deleteProductById(Integer productId);
//
//    Page<Product> getAllProducts(ProductQueryParams productQueryParams);
}
