package com.store.accounting;

public class dailyReport {

    double sales = 0;
    double taxCollected = 0;
    double regularFuelSales = 0;


    double taxRate = 0.062;

    public dailyReport(){}

    public dailyReport(double sales, double taxRate, double taxCollected, double regularFuelSales) {
        this.sales = sales;
        this.taxRate = taxRate;
        this.taxCollected = taxCollected;
        this.regularFuelSales = regularFuelSales;
    }


    public dailyReport getReport(){
        return this;
    }

    public double getSales() {
        return sales;
    }

//    public double getTaxRate() {
//        return taxRate;
//    }

    public double getTaxCollected() {
        return taxCollected;
    }

    public double getRegularFuelSales() {
        return regularFuelSales;
    }
}
