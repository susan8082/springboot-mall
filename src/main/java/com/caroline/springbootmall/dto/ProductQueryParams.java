package com.caroline.springbootmall.dto;

import com.caroline.springbootmall.constant.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private Integer pageIndex;
    private Integer size;
    private Sort sort;

    public void setSort(String descOrAsc, String orderBy){
        if(descOrAsc.equals("desc")){
            this.sort = Sort.by(orderBy).descending();
        }else{
            this.sort = Sort.by(orderBy).ascending();
        }

    }
}
