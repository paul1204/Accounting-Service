package com.store.inventory;

import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class Inventory {


    private int totalQty;

    //List<T> list = new ArrayList<T>();

    private List list = new ArrayList();

    public void Inventory() {
        list = initInventory();
    }



    private List<?>initInventory(){
        return List.of(
                new Drink("Pepsi", 1.99, 5, 1234),
                new Drink("Diet Pepsi", 1.99, 5, 1235),
                new Food("Lays", 1.99, 5, 9876),
                new Food("Kit-Kat", 1.99, 5, 9875),
                new Drink("Coke", 1.99, 5, 2345),
                new Drink("Diet Coke", 1.99, 5, 2346)
        );
    }

    public List<?>getInventory(){
        return list;
    }

}
