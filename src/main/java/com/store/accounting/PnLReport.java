package com.store.accounting;


import com.google.gson.Gson;



public class PnLReport {
    double sales = 0;
    //Cost of Goods Sold
    double cogs = 0;

    double tax = 0;
    double qty = 0;
    double expenses = 0;

    double grossProfit = 0;
    double netProfit = 0;

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales += sales;
    }

    public double getCogs() {
        return cogs;
    }

    public void setCogs(double cogs) {
        this.cogs += cogs;
    }

    public double getTax() {
        return Math.round(this.tax * 100.0) / 100.0;
    }

    public void setTax(double tax) {
        this.tax += tax;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty += qty;
    }

    double getNetProfit(){
        return  Math.round(this.netProfit * 100.0) / 100.0;
    }
    //Expenses will be replaced by actual expenses such as Payroll and General Overhead

    public PnLReport(){
    }

    public PnLReport(double sales, double tax, double qty, double cogs){
        this.sales = sales;
        this.tax = tax;
        this.qty = qty;
        this.cogs = cogs;
    }

    void calcGross() {
        this.grossProfit = sales - cogs;
    }

    //Here is where all the Overhead cost will be calculated
    //Coming Soon
    void calcOverHead() {
        this.expenses += getTax();
    }

    void calcNet() {
        this.netProfit = grossProfit - expenses;
    }

    public void clearReport(){
        this.sales = 0;
        this.cogs = 0;
        this.grossProfit = 0;
        this.expenses = 0;
        this.tax = 0;
        this.netProfit = 0;
    }

    @Override
    public String toString() {
        calcGross();
        calcOverHead();
        calcNet();
        return "PnLReport{" +
                "sales= " + sales +
                ", cogs= " + cogs +
                ", Gross_Pofit= " + grossProfit +
                ", Expenses= " + expenses +
                ", Net_Profit= " + getNetProfit() +
                '}';
    }
}





