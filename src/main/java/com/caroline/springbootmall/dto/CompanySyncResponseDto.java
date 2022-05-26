package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.model.Address;
import com.caroline.springbootmall.model.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CompanySyncResponseDto {
    private String type;
    @JsonProperty("company_number")
    private String companyNumber;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("date_of_creation")
    private String dateOfCreation;
    private String jurisdiction;
    @JsonProperty("company_status")
    private String companyStatus;
    @JsonProperty("registered_office_address")
    private Address registeredOfficeAddress;

    public static Company setCompanySyncInfo(CompanySyncResponseDto companyDto, Company company) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        company.setName(companyDto.getCompanyName());
        company.setDateOfCreation(LocalDate.parse(companyDto.getDateOfCreation(), formatter));
        company.setJurisdiction(companyDto.getJurisdiction());
        company.setCompanyStatus(companyDto.getCompanyStatus());
        company.setRegisteredOfficeAddress(companyDto.getRegisteredOfficeAddress());
        company.setType(companyDto.getType());
        return company;
    }
}