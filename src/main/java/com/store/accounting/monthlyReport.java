package com.store.accounting;

public class monthlyReport {
    double sales = 0;

    double mDrinkSales = 0;
    double mSnackSales = 0;
    double mHotFoodSales = 0;

    double mTaxCollected = 0;
    double mRegularFuelSales = 0;

    //double taxCollected = 0;
    double mTaxRate = 0.062;

    public monthlyReport(dailyReport r){
        this.mDrinkSales = r.drinksSales;
        this.mSnackSales = r.snackSales;
        this.mHotFoodSales = r.hotFoodSales;
        this.mTaxCollected = r.taxCollected;
        this.mRegularFuelSales = r.regularFuelSales;

        if(taxCheck()==false){
            //Error
            //Return Discrepancy To Cash Register
        }
    }
    private boolean taxCheck(){
        if(this.mTaxCollected == (mTaxRate * this.sales) ){
            return true;
        }
        else {
            return false;
        }
    }
}