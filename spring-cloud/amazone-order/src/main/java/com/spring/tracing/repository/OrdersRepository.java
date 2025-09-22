package com.spring.tracing.repository;
import com.spring.tracing.model.Orders;
import org.springframework.data.repository.CrudRepository;



public interface OrdersRepository extends CrudRepository<Orders, Integer> {}