package com.store.accounting;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table
public class dailyReport {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    String date = "";
    double drinksSales = 0;
    double snackSales = 0;
    double hotFoodSales = 0;

    double taxCollected = 0;
    double regularFuelSales = 0;

    double sales = 0;
    //double taxCollected = 0;
    double taxRate = 0.062;



    public dailyReport(dailyReport r){
        this.date += r.date;
        this.drinksSales = r.drinksSales;
        this.snackSales = r.snackSales;
        this.hotFoodSales = r.hotFoodSales;
        this.taxCollected = r.taxCollected;
        this.regularFuelSales = r.regularFuelSales;

        if(taxCheck()==false){
            //Error
            //Return Discrepancy To Cash Register
        }

        this.sales = calcTotal();

    }



    public String getDate() {
        return this.date;
    }


    //create table [if not exists] dailyReport( date date(10), DrinkSale float8, SnackSales float8, HotFoodSales float8, TaxCollected float8, RegularFuelSales float8, TotalSales float8 );


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

    public double getDrinksSales() {
        return drinksSales;
    }

    public double getSnackSales() {
        return snackSales;
    }

    public double getHotFoodSales() {
        return hotFoodSales;
    }

    public double getTaxRate() {
        return taxRate;
    }

    @Override
    public String toString() {
        return "dailyReport{" +
                "date='" + date + '\'' +
                ", drinksSales=" + drinksSales +
                ", snackSales=" + snackSales +
                ", hotFoodSales=" + hotFoodSales +
                ", taxCollected=" + taxCollected +
                ", regularFuelSales=" + regularFuelSales +
                ", sales=" + sales +
                ", taxRate=" + taxRate +
                '}';
    }
}
