package com.amazone.service;

import com.amazone.model.Inventory;
import com.amazone.model.Orders;
import com.amazone.repository.InventoryRepository;
import com.amazone.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


import java.util.ArrayList;
import java.util.List;



@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    OrdersRepository ordersRepository;
    public List<Inventory> getAllInventories() {
        List<Inventory> sells = new ArrayList<>();
        inventoryRepository.findAll().forEach(product -> sells.add(product));
        return sells;
    }
public Date convertStringToDate(String date){
    int day=Integer.parseInt(date.split("-")[0]);
    int month=Integer.parseInt((date.split("-")[1]));
    int year=Integer.parseInt((date.split("-")[2]));
    Date date1=new Date(day,month,year);
    return date1;
}
public Inventory getInventoryForDate(Date date){
    List<Orders> allOrders = new ArrayList<>();
    ordersRepository.findAll().forEach(order -> allOrders.add(order));
    Inventory inventory=new Inventory(date,0,0);
    for(Orders orders:allOrders){
        inventory.setSalesCount(inventory.getSalesCount()+1);
        inventory.setTotalPrice(inventory.getTotalPrice()+orders.getPrice());

    }
    inventoryRepository.save(inventory);
    System.out.println("Total Price for date : "+date+" is "+inventory.getTotalPrice());
    return inventory;
}
    public void delete(int id) {
        inventoryRepository.delete(id);
    }
    public Inventory getInventoryById(int id) {
        return inventoryRepository.findOne(id);
    }

    public int save(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory.getId();
    }


}

