package com.store.accounting;


import javax.persistence.*;


@Entity
@Table
public class PnLReport {

    @Id
    @SequenceGenerator(
            name = "pnl_sequence",
            sequenceName = "pnl_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pnl_sequence"
    )
    double income = 0;
    //Cost of Goods Sold
    double cogs = 0;
    double grossProfit = 0;
    double expenses = 0;
    double netProfit = 0;
    double tax = 0;

    public void setSales(double sales) {
        this.income += sales;
    }

    public void setCogs(double cogs, double qty) {
        this.cogs += cogs * qty;
    }

    //Tax is not an expense? Need to refresh my memory on that
    public double getTax() {
        return Math.round(this.tax * 100.0) / 100.0;
    }

    public void setTax(double tax) {
        this.tax += tax;
    }

    double getGrossProfit(){
        return  Math.round(this.grossProfit * 100.0) / 100.0;
    }


    double getNetProfit(){
        return  Math.round(this.netProfit * 100.0) / 100.0;
    }
    //Expenses will be replaced by actual expenses such as Payroll and General Overhead

    public PnLReport(){
    }

    void calcGross() {
        this.grossProfit = income - cogs;
    }

    //Here is where the expenses will be calculated
    //
    void calcOverHead() {
        //Suppose tempVal represents all the expenses a company will accumulate
        //A more detailed breakdown will be reported soon
        double tempVal = 50.00;
        this.expenses += tempVal;
    }

    void calcNet() {
        this.netProfit = grossProfit - expenses;
    }

    public void clearReport(){
        this.income = 0;
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
                "Income= " + income +
                ", cogs= " + cogs +
                ", Gross_Pofit= " + getGrossProfit() +
                ", Expenses= " + expenses +
                ", Net_Profit= " + getNetProfit() +
                '}';
    }
}





