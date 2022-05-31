package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.*;
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

    final String scheme = "http";
    final String host = "download.companieshouse.gov.uk";
    final String field = "BasicCompanyDataAsOneFile";
    final String fileType=".zip";

    @Autowired
    private UtilService utilService;

    @Autowired
    private FileService fileService;

    @Override
    public void downloadMonthlyCompanyData(String date) {

        try {
            URL resourceUrl =  new URL(utilService.buildUrlPath(scheme, host, String.format("/%s-%s%s", field, date, fileType )));
            File targetFile = new File(String.format("%s%s%s-%s%s", new File("").getAbsolutePath(), File.separator, field, date, fileType));
            fileService.downloadFileFromUrl(resourceUrl, targetFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}



