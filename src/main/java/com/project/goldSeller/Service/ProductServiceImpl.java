package com.project.goldSeller.Service;

import com.project.goldSeller.Entity.Product;
import com.project.goldSeller.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
    productRepository.deleteById(id);
    }

    @Override
    public List<Product> displayProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> displayProductById(Long id) {
        return productRepository.findById(id);
    }
}
