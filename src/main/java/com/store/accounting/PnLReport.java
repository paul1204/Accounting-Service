package com.store.accounting;


import com.google.gson.Gson;



public class PnLReport {
    Gson gson = new Gson();

    double sales = 0;
    //Cost of Goods Sold
    double cogs = 0;
    double grossProfit = 0;
    double netProfit = 0;

    //Expenses will be replaced by actual expenses such as Payroll and General Overhead
    double expenses = 0;

    //Cogs will be pulled from Daily Report
    //Cash Register does report Cost of Goods Sold
    //Accounting Service does not take in Cogs yet
    //The arguments will come from a Monthly Report
    //Not yet implemented
    //Hard Coding monthlySale and monnthlyCogs
    PnLReport(double monthlySale, double monthlyCogs) {
    //    this.sales = 5000.00;
      //  this.cogs = 100.00;
    }

    PnLReport(){}

    void setMonthlySales(){this.sales = 5000.00; this.cogs = 100.00;}


    void calcGross() {
        this.grossProfit = sales - cogs;
    }

    void calcOverHead() {
        this.expenses = 200;
    }

    void calcNet() {
        this.netProfit = grossProfit - expenses;
    }

    String generatePnL(){
        setMonthlySales();
        calcGross();
        calcOverHead();
        calcNet();
        double[] n = {sales, cogs, grossProfit, expenses, netProfit};
        return gson.toJson(n);
    }
}






//    public static void main(String[] args){
//
//
//        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
//        String numbersJson = gson.toJson(numbers);
//
//
//        System.out.println(numbersJson);
//    }




