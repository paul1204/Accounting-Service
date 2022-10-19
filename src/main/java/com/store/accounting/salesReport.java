package com.store.accounting;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales_report")
public class salesReport {

    @Id
    @Column
    String date = "";

    @Column
    double sales = 0;
    @Column
    double tax_collected = 0;
    @Column
    double qty = 0;
    @Column
    double cogs = 0;

    public salesReport(double sales, double tax, double qty, double cogs) {
        this.sales = sales;
        this.tax_collected = tax;
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
        return tax_collected;
    }

    public void setTax(double tax) {
        this.tax_collected = tax;
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
