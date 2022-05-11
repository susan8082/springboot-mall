package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDto {

    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;
    private String description;

    public static Product convertToProduct(ProductRequestDto productRequestDto) {
        Product productEntity = new Product();
        productEntity.setProductName(productRequestDto.getProductName());
        productEntity.setCategory(productRequestDto.getCategory());
        productEntity.setImageUrl(productRequestDto.getImageUrl());
        productEntity.setPrice(productRequestDto.getPrice());
        productEntity.setStock(productRequestDto.getStock());
        productEntity.setDescription(productRequestDto.getDescription());
        productEntity.setCreatedDate(LocalDate.now());
        productEntity.setLastModifiedDate(LocalDate.now());
        return productEntity;
    }
}
