package com.store.inventory;

import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class Inventory {


    private int totalQty;

    //List<T> list = new ArrayList<T>();

    private List l = new ArrayList();

    public Inventory() {
        initInventory();
    }

    private void initInventory(){

                l.add(new Drink("Pepsi", 1.99, 5, 1234));
                l.add(new Drink("Diet Pepsi", 1.99, 5, 1235));
                l.add(new Food("Lays", 1.99, 5, 9876));
                l.add(new Food("Kit-Kat", 1.99, 5, 9875));
                l.add(new Drink("Coke", 1.99, 5, 2345));
                l.add(new Drink("Diet Coke", 1.99, 5, 2346));

    }

    public List getInventory(){
        return l;
    }

    public void addIventory(){

    }

}
