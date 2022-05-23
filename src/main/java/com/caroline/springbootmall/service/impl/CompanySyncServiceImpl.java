package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.CompanyDao;
import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanySyncServiceImpl implements CompanyService {

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


//    @Override
//    public void updateProduct(Integer productId, ProductRequestDto productDto) {
//        productDao.updateProduct(productId, productDto);
//    }
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

}
