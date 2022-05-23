package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyRequestDto {

    @NotNull
    private String companyNumber;
    @NotNull
    private String name;

    public static Company convertToProduct(CompanyRequestDto companyDto) {
        Company company = new Company();
        company.setCompanyNumber(companyDto.getCompanyNumber());
        company.setName(companyDto.getName());
        company.setTimeOfRegister(LocalDateTime.now());
        return company;
    }
}
