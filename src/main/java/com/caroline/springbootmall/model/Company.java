package com.caroline.springbootmall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Company {

    @Id
    private String companyNumber;
    private String name;
    private String type;
    private LocalDate dateOfCreation;
    //register web account
    private LocalDateTime timeOfRegister;
    private String jurisdiction;
    private String companyStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="companyAddressId", referencedColumnName = "addressId")
    private Address registeredOfficeAddress;

}
