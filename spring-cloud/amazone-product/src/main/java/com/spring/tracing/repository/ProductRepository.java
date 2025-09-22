package com.spring.tracing.repository;
import com.spring.tracing.model.Product;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends CrudRepository<Product, Integer> {}