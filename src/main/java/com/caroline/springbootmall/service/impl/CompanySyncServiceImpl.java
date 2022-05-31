package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.CompanySyncService;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
import com.caroline.springbootmall.service.UtilService;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompanySyncServiceImpl implements CompanySyncService {

    String scheme = "https";
    String host = "api.company-information.service.gov.uk";
    String field = "company";
    String downloadHost = "download.companieshouse.gov.uk";
    String downloadField = "BasicCompanyDataAsOneFile-";
    String downloadDate = "2022-05-01";
    String fileType=".zip";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UtilService utilService;

    @Override
    public Company syncCompanyByCompanyNum(String companyNum) {

//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(String.format(URI, field, companyNum)))
//                    .header("Authorization", "Basic NTE4MDEwZTYtNTU2OC00ZjkxLWEyYWItM2NmZmU5ZDNhZGM2Og==")
//                    .GET()
//                    .build();
//            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println("String.format(URI, field, companyNum):"+String.format(URI, field, companyNum));
//            System.out.println("request.uri():"+request.uri());
//            System.out.println(response);
//            System.out.println(response.body());
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //prepate Http headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic NTE4MDEwZTYtNTU2OC00ZjkxLWEyYWItM2NmZmU5ZDNhZGM2Og==");
        HttpEntity request = new HttpEntity(headers);
        CompanySyncResponseDto companyResDto = null;
        //send Http request & get response
        ResponseEntity<CompanySyncResponseDto> response = new RestTemplate()
                .exchange(utilService.buildUrlPath(scheme, host, String.format("/%s/%s", field, companyNum)), HttpMethod.GET , request, CompanySyncResponseDto.class);
        companyResDto  = response.getBody();


        companyService.updateCompany(companyNum, companyResDto);
        return companyService.getCompanyByCompanyNum(companyNum);
    }


    @Override
    public Company syncAllCompany(List<String> companyNum) {

        return null;
    }

}



