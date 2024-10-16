package com.project.goldSeller.RestController;


import com.project.goldSeller.Entity.Product;
import com.project.goldSeller.Repository.ProductRepository;
import com.project.goldSeller.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;



@RestController
@CrossOrigin("*")
@RequestMapping(value = "/product")
public class ProductRestController {
@Autowired
    ProductService productService;
@Autowired
    ProductRepository productRepository;
    @RequestMapping(method = RequestMethod.POST)
    public Product addProduct (@RequestBody Product product){
        return productService.addProduct( product);
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Product updateProduct (@PathVariable ("id")Long id,@RequestBody Product product){
        Product savedProduct=productRepository.save(product);
        Product newProduct =productService.updateProduct(product);
        return newProduct;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id")Long id){
        productService.deleteProduct(id);
    }
    @RequestMapping(method = RequestMethod.GET )
    public List<Product> displayProduct(){
        return productService.displayProduct();
    }
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Product> getProductById(@PathVariable("id") Long id){

        Optional<Product> product= productService.displayProductById(id);
        return product;
    }
}
