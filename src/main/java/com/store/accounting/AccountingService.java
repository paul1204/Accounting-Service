package com.store.accounting;

public class AccountingService {

    dailyReport report;

    public AccountingService(){}

    public void reportDaily(dailyReport r){
        report = new dailyReport(r);
        System.out.println(r.getSales());
        System.out.println(r.getTaxCollected());
        System.out.println(r.getRegularFuelSales());
        //Add to Database
    }
}
