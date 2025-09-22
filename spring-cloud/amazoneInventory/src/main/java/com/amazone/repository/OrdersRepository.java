package com.amazone.repository;
import com.amazone.model.Orders;
import org.springframework.data.repository.CrudRepository;



public interface OrdersRepository extends CrudRepository<Orders, Integer> {}