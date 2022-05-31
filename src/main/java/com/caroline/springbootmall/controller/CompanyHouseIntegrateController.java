package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyDownloadService;
import com.caroline.springbootmall.service.CompanySyncService;
import com.caroline.springbootmall.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyHouseIntegrateController {
    @Autowired
    private CompanySyncService companySyncService;
    @Autowired
    private CompanyDownloadService companyDownloadService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/companySync/{companyNumber}")
    public ResponseEntity<Company> syncCompanyByCompanyNum(@PathVariable String companyNumber){
            Company company = companySyncService.syncCompanyByCompanyNum(companyNumber);
            if (company != null){
                return ResponseEntity.status(HttpStatus.OK).body(company);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

    @GetMapping("/companyDownload/{targetDate}")
    public ResponseEntity<String> downloadCompanyDataByTargetDate(@PathVariable String targetDate){

        if(targetDate.substring(6,8).equals("01")){
            String formatedDate = utilService.formatDateString(targetDate, "-");
            companyDownloadService.downloadMonthlyCompanyData(formatedDate);

            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
