package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.CompanyDao;
import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public Company getCompanyByCompanyNum(String companyNum) {
        return companyDao.getCompanyByCompanyNum(companyNum);
    }

//    @Override
//    public Company createCompany(CompanyRequestDto companyDto) {
//        return companyDao.createCompany(companyDto);
//    }


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
