package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyDownloadService;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.CompanySyncService;
import com.caroline.springbootmall.service.UtilService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class CompanyDownloadServiceImpl implements CompanyDownloadService {

//    http://download.companieshouse.gov.uk/BasicCompanyDataAsOneFile-2022-05-01.zip
    String scheme = "http";
    String host = "download.companieshouse.gov.uk";
    String field = "BasicCompanyDataAsOneFile";
    String targetDate = "2022-05-01";
    String fileType=".zip";


    @Autowired
    private UtilService utilService;



    @Override
    public void downloadMonthlyCompanyData(String date) {

        try {
            FileUtils.copyURLToFile(new URL(utilService.buildUrlPath(scheme, host, String.format("/%s-%s%s", field,targetDate, fileType ))), new File(String.format("%s%s%s-%s%s", new File("").getAbsolutePath(), File.separator, field, date, fileType)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}



