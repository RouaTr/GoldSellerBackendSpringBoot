package com.project.goldSeller.Service;

import com.project.goldSeller.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product>displayProduct();
    Optional<Product>displayProductById(Long id);
}
