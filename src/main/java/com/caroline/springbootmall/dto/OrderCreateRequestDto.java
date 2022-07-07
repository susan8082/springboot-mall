package com.caroline.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderCreateRequestDto {

    @NotEmpty
    private List<BuyItem> buyItemList;
}
