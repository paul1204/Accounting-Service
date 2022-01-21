package com.store.inventory;


import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class inventory {

    Inventory i = new Inventory();

    @GetMapping(path = "inventory")
    public String home(){
        return "Hello, use inventory/all endpoint to view all stock";
    }

    @GetMapping(path = "inventory/current")
    public List getAll(){
        return i.getInventory();
    }

    @PutMapping(path = "inventory/delivery")
    public void add(){

    }

}

