package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.CompanySyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanySyncController {
    @Autowired
    private CompanySyncService companySyncService;

    @GetMapping("/companySync/{companyNumber}")
    public ResponseEntity<Company> syncCompanyByCompanyNum(@PathVariable String companyNumber){
            Company company = companySyncService.syncCompanyByCompanyNum(companyNumber);
            if (company != null){
                return ResponseEntity.status(HttpStatus.OK).body(company);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

}
