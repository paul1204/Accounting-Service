package com.store.accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table
public class monthlyReport {


//    String month = "";
//    String day = "";
//    String year = "";

    @Id
    @Column
    String monYr = "";
    @Column
    double sales = 0;
    @Column
    double mDrinkSales = 0;
    @Column
    double mSnackSales = 0;
    double mHotFoodSales = 0;

    double mTaxCollected = 0;
    double mRegularFuelSales = 0;

    //double taxCollected = 0;
    final double mTaxRate = 0.062;

    public monthlyReport(dailyReport r) {
        this.mDrinkSales += r.drinksSales;
        this.mSnackSales += r.snackSales;
        this.mHotFoodSales += r.hotFoodSales;
        this.mTaxCollected += r.taxCollected;
        this.mRegularFuelSales += r.regularFuelSales;

        if (taxCheck() == false) {
            //Error
            //Return Discrepancy To Cash Register
        }
    }

    private boolean taxCheck() {
        if (this.mTaxCollected == (mTaxRate * this.sales)) {
            return true;
        } else {
            return false;
        }
    }

}