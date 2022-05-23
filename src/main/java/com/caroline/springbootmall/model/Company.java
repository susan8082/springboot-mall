package com.caroline.springbootmall.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String dateOfCreation;
    //register web account
    private LocalDateTime TimeOfRegister;

}
