package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.constant.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
}
