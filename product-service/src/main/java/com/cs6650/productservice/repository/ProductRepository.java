package com.cs6650.productservice.repository;

import com.cs6650.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> { // <class, identifier type>

}
