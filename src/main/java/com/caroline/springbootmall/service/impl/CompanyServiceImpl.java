package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.CompanyDao;
import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.CompanySyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    private CompanyDao companyDao;

    @Override
    public Company getCompanyByCompanyNum(String companyNum) {
        return companyDao.getCompanyByCompanyNum(companyNum);
    }

    @Override
    public Company createCompany(CompanyRequestDto companyDto) {
        return companyDao.createCompany(companyDto);
    }

    @Override
    public void updateCompany(String companyNum, CompanyRequestDto companyReqDto) {
       companyDao.updateCompany(companyNum, companyReqDto);
    }

    @Override
    public void updateCompany(String companyNum, CompanySyncResponseDto companySyncResponseDto) {
        companyDao.updateCompany(companyNum, companySyncResponseDto);
    }
}
//
//    @Override
//    public void deleteProductById(Integer productId) {
//        productDao.deleteProductById(productId);
//
//    }
//
//    @Override
//    public Page<Product> getAllProducts(ProductQueryParams productQueryParams) {
//        return productDao.getAllProducts(productQueryParams);
//    }


