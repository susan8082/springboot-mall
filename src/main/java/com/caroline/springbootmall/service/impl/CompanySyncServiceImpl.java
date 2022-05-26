package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dto.CompanySyncResponseDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.service.CompanyService;
import com.caroline.springbootmall.service.CompanySyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanySyncServiceImpl implements CompanySyncService {

    final String URI = "https://api.company-information.service.gov.uk/%s/%s";
    final String field = "company";
    @Autowired
    private CompanyService companyService;

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

        //send Http request & get response
        ResponseEntity<CompanySyncResponseDto> response = new RestTemplate().exchange(String.format(URI, field, companyNum), HttpMethod.GET , request, CompanySyncResponseDto.class);
        CompanySyncResponseDto companyResDto = response.getBody();

        companyService.updateCompany(companyNum, companyResDto);
        return companyService.getCompanyByCompanyNum(companyNum);
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


