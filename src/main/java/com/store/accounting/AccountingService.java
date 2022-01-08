package com.store.accounting;

public class AccountingService {

    dailyReport r = new dailyReport();

    public AccountingService(){}

    public void reportDaily(dailyReport r){
        System.out.println(r.getSales());
        System.out.println(r.getTaxCollected());
        System.out.println(r.getRegularFuelSales());
        //Add to Database
    }

}
