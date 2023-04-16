package com.cs6650.productservice.service;

import com.cs6650.productservice.dto.AuctionItemRequest;
import com.cs6650.productservice.dto.ProductRequest;
import com.cs6650.productservice.dto.ProductResponse;
import com.cs6650.productservice.model.Product;
import com.cs6650.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient webClient;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse getById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
        }
        return null;
    }

    public void productAction(String id, BigDecimal price) {
        ProductResponse productResponse  = this.getById(id);
        AuctionItemRequest auctionItemRequest = AuctionItemRequest.builder().id(id).name(productResponse.getName()).startPrice(price).build();
        webClient.post().uri("http://localhost:8080/api/action").contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(auctionItemRequest), AuctionItemRequest.class);
    }

}
