package com.spring.tracing.controllers;
import com.spring.tracing.model.Product;
import com.spring.tracing.service.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    AddProductService addProductService;

    @GetMapping("/products")
    private List<Product> getAllProducts() {
        return addProductService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    private Optional< Product> getProductById(@PathVariable("id") int id) {
        return addProductService.getProductById(id);
    }
    @GetMapping("/products/getprice")
    public float getprice() {
        return addProductService.getProductPrice(1);
    }

    @DeleteMapping("/products/{id}/delete")
    private void deleteProduct(@PathVariable("id") int id) {
        addProductService.delete(id);
    }

    @PostMapping("/products/AddProduct")
    private int saveProduct(@RequestBody Product product) {
        addProductService.save(product);
        return product.getId();
    }
    @GetMapping("/products/getproduct")
    public String login() {
         return  addProductService.login();

    }

}
