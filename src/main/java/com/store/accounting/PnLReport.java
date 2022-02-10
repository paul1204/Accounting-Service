package com.store.accounting;

public class PnLReport {
    double sales = 0;
    //Cost of Goods Sold
    double cogs = 0;
    double grossProfit = 0;


    double expenses = 0;


    //Cogs will be pulled from Daily Report
    //Cash Register does report Cost of Goods Sold
    //Accounting Service does not take in Cogs yet
    PnLReport(double s, double cogs){
        this.sales = s;
        this.cogs = 0;
    }

    double calcGross(){ return grossProfit = sales - cogs; }



}
