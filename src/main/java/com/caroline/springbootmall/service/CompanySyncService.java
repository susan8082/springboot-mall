package com.caroline.springbootmall.service;


import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.model.Company;

import java.util.List;

public interface CompanySyncService {

    Company syncCompanyByCompanyNum(String companyNum);
    Company syncAllCompany(List<String> companyNum);

}
