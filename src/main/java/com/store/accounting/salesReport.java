package com.store.accounting;

public class salesReport {


    double sales = 0;
    double tax = 0;
    double qty = 0;
    double cogs = 0;


    public salesReport(double sales, double tax, double qty, double cogs) {
        this.sales = sales;
        this.tax = tax;
        this.qty = qty;
        this.cogs = cogs;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getCogs() {
        return cogs;
    }

    public void setCogs(double cogs) {
        this.cogs = cogs;
    }
}
