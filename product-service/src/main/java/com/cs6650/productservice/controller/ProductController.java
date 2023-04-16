package com.cs6650.productservice.controller;

import com.cs6650.productservice.dto.ProductRequest;
import com.cs6650.productservice.dto.ProductResponse;
import com.cs6650.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody  ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
         return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable String id){
        return productService.getById(id);
    }

    @PostMapping(value = "/{id}/{price}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void productAuction(@PathVariable String id, @PathVariable BigDecimal price){
        productService.productAction(id, price);
    }

}
