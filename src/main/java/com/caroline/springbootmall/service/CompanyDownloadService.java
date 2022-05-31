package com.caroline.springbootmall.service;


import com.caroline.springbootmall.model.Company;

import java.util.List;

public interface CompanyDownloadService {

    void downloadMonthlyCompanyData(String date);

}
