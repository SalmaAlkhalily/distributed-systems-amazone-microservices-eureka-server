package com.amazone.repository;
import com.amazone.model.Inventory;
import org.springframework.data.repository.CrudRepository;



public interface InventoryRepository extends CrudRepository<Inventory, Integer> {}
