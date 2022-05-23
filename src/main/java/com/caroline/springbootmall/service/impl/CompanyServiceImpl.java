package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanySyncService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanySyncService {

    final String URI = "https://api.company-information.service.gov.uk/%s/%s";
    final String field = "company";


    @Override
    public Company syncCompanyByCompanyNum(String companyNum) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(String.format(URI, field, companyNum)))
                    .header("Authorization", "Basic NTE4MDEwZTYtNTU2OC00ZjkxLWEyYWItM2NmZmU5ZDNhZGM2Og==")
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("String.format(URI, field, companyNum):"+String.format(URI, field, companyNum));
            System.out.println("request.uri():"+request.uri());
            System.out.println(response);
            System.out.println(response.body());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Company syncAllCompany(List<String> companyNum) {
        return null;
    }
}


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


