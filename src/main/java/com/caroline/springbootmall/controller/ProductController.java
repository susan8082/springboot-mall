package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.dto.ProductQueryParams;
import com.caroline.springbootmall.dto.ProductRequestDto;
import com.caroline.springbootmall.dto.ProductResponseDto;
import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if (product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequestDto productDto){

        Product returnProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnProduct);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,@RequestBody @Valid ProductRequestDto productDto){

        Product product = productService.getProductById(productId);
        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.updateProduct(productId, productDto);

        Product updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponseDto> getAllProducts(
            //Filtering
            @RequestParam(required = false) ProductCategory category, @RequestParam(required = false) String search,
            //Paging
            @RequestParam(defaultValue = "0")  @Min(0) Integer pageIndex ,  @RequestParam(defaultValue = "5") @Max (1000) @Min(1) Integer size,
            //Sort
            @RequestParam(defaultValue = "desc") String descOrAsc, @RequestParam(defaultValue = "price") String orderBy){

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setPageIndex(pageIndex);
        productQueryParams.setSize(size);
        productQueryParams.setSort(descOrAsc, orderBy);

        Page<Product> products = productService.getAllProducts(productQueryParams);
        ProductResponseDto resDto = new ProductResponseDto(category, search, pageIndex, size, descOrAsc, orderBy, products.getTotalElements(), products.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

}

