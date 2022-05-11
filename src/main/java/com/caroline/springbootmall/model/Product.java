package com.caroline.springbootmall.model;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dto.ProductRequestDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;


}
