package com.caroline.springbootmall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BasicCompanyDataDto {

    private String companyName;
    private String companyNumber;
    private String companyCategory;
    private String companyStatus;
    private String countryOfOrigin;
    private String dissolutionDate;
    private String incorporationDate;
    private String confStmtNextDueDate;
    private String confStmtLastMadeUpDate;

    public BasicCompanyDataDto (String str){
        String [] results =  str.split(",");
        this.setCompanyName(results[0]);
        this.setCompanyNumber(results[1]);
        this.setCompanyCategory(results[10]);
        this.setCompanyStatus(results[11]);
        this.setCountryOfOrigin(results[12]);
        this.setDissolutionDate(results[13]);
        this.setIncorporationDate(results[14]);
        this.setConfStmtNextDueDate(results[52]);
        this.setConfStmtLastMadeUpDate(results[53]);
        System.out.println(this.getCompanyName()+"/"+this.getCompanyNumber());
    }

    public static List<BasicCompanyDataDto> of(List<String> strs){
       return strs.stream().map(str->new BasicCompanyDataDto(str)).collect(Collectors.toList());
    }

}
