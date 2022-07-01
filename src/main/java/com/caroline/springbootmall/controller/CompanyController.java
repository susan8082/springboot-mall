package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.dto.ProductResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companys/{companyNumber}")
    public ResponseEntity<Company> getCompanytByCompanyNum(@PathVariable String companyNumber){
        Company company = companyService.getCompanyByCompanyNum(companyNumber);
        if (company != null){
            return ResponseEntity.status(HttpStatus.OK).body(company);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/companys")
    public ResponseEntity<Company> createCompany(@RequestBody @Valid CompanyRequestDto companyDto){

        Company returnCompany = companyService.createCompany(companyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnCompany);
    }

    @PatchMapping("/companys/{companyNumber}")
    public ResponseEntity<Company> updateCompany(@PathVariable String companyNumber, @RequestBody CompanyRequestDto companyDto){

        companyService.updateCompany(companyNumber, companyDto);

        Company updateCompany = companyService.getCompanyByCompanyNum(companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(updateCompany);
    }



}

