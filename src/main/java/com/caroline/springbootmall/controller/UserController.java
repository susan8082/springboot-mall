package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.dto.CompanyRequestDto;
import com.caroline.springbootmall.dto.UserRequestDto;
import com.caroline.springbootmall.model.Company;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto dto){
       User newUser = userService.createUser(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


//    @PostMapping("/companys")
//    public ResponseEntity<Company> createCompany(@RequestBody @Valid CompanyRequestDto companyDto){
//
//        Company returnCompany = companyService.createCompany(companyDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(returnCompany);
//    }


}
