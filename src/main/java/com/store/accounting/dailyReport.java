package com.store.accounting;

public class dailyReport {

    double sales = 0;

    double drinksSales = 0;
    double snackSales = 0;
    double hotFoodSales = 0;

    double taxCollected = 0;
    double regularFuelSales = 0;

    //double taxCollected = 0;
    double taxRate = 0.062;

    public dailyReport(dailyReport r){
        this.drinksSales = r.drinksSales;
        this.snackSales = r.snackSales;
        this.hotFoodSales = r.hotFoodSales;
        this.taxCollected = r.taxCollected;
        this.regularFuelSales = r.regularFuelSales;

        if(taxCheck()==false){
            //Error
            //Return Discrepancy To Cash Register
        }

    }

    public dailyReport(double drinksSales, double snackSales, double hotFoodSales, double taxCollected, double regularFuelSales) {
        this.drinksSales = drinksSales;
        this.snackSales = snackSales;
        this.hotFoodSales = hotFoodSales;
        this.taxCollected = taxCollected;
        this.regularFuelSales = regularFuelSales;
       // this.taxCollected = taxCollected;
        this.sales = calcTotal();
    }

    private boolean taxCheck(){
        if(this.taxCollected == (taxRate * this.sales) ){
            return true;
        }
        else {
            return false;
        }
    }

    public dailyReport getReport(){
        return this;
    }

    public double getSales() {
        return this.sales;
    }

    public double calcTotal(){
        return (this.drinksSales + this.hotFoodSales +  this.snackSales);
    }

    public double getTaxCollected() {
        return taxCollected;
    }

    public double getRegularFuelSales() {
        return regularFuelSales;
    }
}
