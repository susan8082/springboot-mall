package com.caroline.springbootmall.dao.impl;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dao.CompanyDao;
import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dao.repository.CompanyRepository;
import com.caroline.springbootmall.dao.repository.ProductRepository;
import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CompanyDaoJpaImpl implements CompanyDao {

    @Autowired
    CompanyRepository companyRepo;

//    @Override
//    public Product getProductById(Integer productId) {
//        List<Product> products = productRepo.findByProductId(productId);
//        if (products.size()>0){
//            return products.get(0);
//        }else{
//            return null;
//        }
//
//    }

    @Override
    public Company getCompanyByCompanyNum(String  companyNumber) {
        return companyRepo.findByCompanyNumber(companyNumber);
    }

    @Override
    public Company createCompany(CompanyRequestDto companyDto) {
        Company company = CompanyRequestDto.convertToCompany( companyDto);
        return companyRepo.save(company);
    }

    @Override
    public void updateCompany(String companyNum, CompanyRequestDto company) {
        Company originCompany = getCompanyByCompanyNum(companyNum);
        originCompany.setName(company.getName());
        companyRepo.save(originCompany);
    }

    @Override
    public void updateCompany(String companyNum, CompanySyncResponseDto companyRequestDto) {
        Company originCompany = getCompanyByCompanyNum(companyNum);
        Company updateCompany = CompanySyncResponseDto.setCompanySyncInfo(companyRequestDto, originCompany);
        companyRepo.save(updateCompany);
    }

//    @Override
//    public Product createProduct(ProductRequestDto productDto) {
//        Product product = ProductRequestDto.convertToProduct(productDto);
//        product.setCreatedDate(LocalDateTime.now());
//        return productRepo.save(product);
//
//    }
//
//    @Override
//    public void updateProduct(Integer productId, ProductRequestDto productDto) {
//        Product  originProduct = getProductById(productId);
//
//        Product updateProduct = ProductRequestDto.convertToProduct(productDto);
//        updateProduct.setCreatedDate(originProduct.getCreatedDate());
//        updateProduct.setProductId(productId);
//        productRepo.save(updateProduct);
//    }
//
//    @Override
//    public void deleteProductById(Integer productId) {
//        Integer result = productRepo.deleteProductByProductId(productId);
//        System.out.println("deleteProductById:"+result);
//    }
//
//    @Override
//    public Page<Product> getAllProducts(ProductQueryParams productQueryParams) {
//        ProductCategory category = productQueryParams.getCategory();
//        String search = productQueryParams.getSearch();
//        Pageable paging = PageRequest.of(productQueryParams.getPageIndex(), productQueryParams.getSize(), productQueryParams.getSort());
//        if (category != null && search != null){
//            return productRepo.findAllByCategoryAndProductNameContaining(category, search, paging);
//        }else if (category != null){
//            return productRepo.findAllByCategory(category, paging);
//        }else if (search != null){
//            return productRepo.findAllByProductNameContaining(search, paging);
//        }else{
//            return productRepo.findAll(paging);
//        }
//
    }


