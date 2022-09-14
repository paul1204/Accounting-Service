package com.store.products;

public class Drink {

    public String name;
    public double price;
    public int qty;
    public int upc;

    public Drink(String name, double price, int qty, int upc) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.upc = upc;
    }

    public void addQty(int q){this.qty += q;}
    public int getQty(){return this.qty;}

}
