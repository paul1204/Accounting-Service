package com.store.inventory;


import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "inventory/all")
class InventoryController {

    Inventory i = new Inventory();

    @GetMapping
    public List get(){
        //return i.getInventory();
        return List.of(
                new Drink("Pepsi", 1.99, 5, 1234),
                new Drink("Diet Pepsi", 1.99, 5, 1235),
                new Food("Lays", 1.99, 5, 9876),
                new Food("Kit-Kat", 1.99, 5, 9875),
                new Drink("Coke", 1.99, 5, 2345),
                new Drink("Diet Coke", 1.99, 5, 2346)
        );
    }
}

@RestController
@RequestMapping(path = "inventory")
class home {

    @GetMapping
    public String get(){
        return "Hello, use inventory/all endpoint to view all stock";
    }
}

@RestController
@RequestMapping(path = "inventory/delivery")
class delivery {

    @PutMapping
    public List get(){
        //return i.getInventory();
        return List.of(
                new Drink("Pepsi", 1.99, 5, 1234),
                new Drink("Diet Pepsi", 1.99, 5, 1235),
                new Food("Lays", 1.99, 5, 9876),
                new Food("Kit-Kat", 1.99, 5, 9875),
                new Drink("Coke", 1.99, 5, 2345),
                new Drink("Diet Coke", 1.99, 5, 2346)
        );
    }
}
