package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private ProductCategory category;
    private String search;
    private Integer pageIndex;
    private Integer size;
    private String descOrAsc;
    private String orderBy;
    private List<Product> products;
}
