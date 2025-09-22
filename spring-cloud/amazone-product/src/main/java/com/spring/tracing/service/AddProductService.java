package com.spring.tracing.service;
import com.spring.tracing.model.Product;
import com.spring.tracing.model.User;
import com.spring.tracing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AddProductService {

    @Autowired
    ProductRepository productRepository;
@Autowired
UserService userService;
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }
    public float getProductPrice(int id) {
       Optional<Product>product= productRepository.findById(id);
      return product.get().getPrice();
    }
    public void save(Product product) {
        User user=new User("salma","alkhalili","salma@gmail.com","123");
        userService.Login(user);
        productRepository.save(product);
    }
    public String login() {
        User user=new User("salma","alkhalili","salma@gmail.com","123");
      return   userService.Login(user);
    }


}

