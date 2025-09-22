package com.amazone.controllers;

import com.amazone.model.Inventory;
import com.amazone.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class InventoryController {
    @Autowired
InventoryService inventoryService=new InventoryService();
    @PostMapping("/inventory/addInventory")
    private int saveInventory(@RequestBody Inventory inventory) {
      return  inventoryService.save(inventory);

    }
    @PostMapping("/inventory/findInventoryForDate")
    private Inventory getInventoryByDate(@RequestBody String inventoryDate) {
        Date d=  inventoryService.convertStringToDate(inventoryDate);
       return inventoryService.getInventoryForDate(d);

    }
    @GetMapping("/inventory")
    private List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

}
