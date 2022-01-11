package com.store.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountingService {

    dailyReport report;
    monthlyReport monthlyReport;
    private AccountingRepo repo;

    @Autowired
    public AccountingService(AccountingRepo r){
        this.repo = r;
    }

    @Transactional
    public void reportDaily(dailyReport r){
        report = new dailyReport(r);
        pushDb(report);
        System.out.println(report.toString());
        //Add to Database
    }

    public dailyReport getMonthlyReport() {
        return null;
    }


    private void pushDb(dailyReport r){
        repo.updateDaily(r.getDate(), r.getDrinksSales(), r.getHotFoodSales(), r.getRegularFuelSales(), r.getSales(), r.getSnackSales(), r.getTaxCollected(), r.getTaxRate());
    }



}
