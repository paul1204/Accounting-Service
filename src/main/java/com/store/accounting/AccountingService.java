package com.store.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;


@Service
public class AccountingService {

    dailyReport report;
    monthlyReport monthlyReport;
    private AccountingRepo repo;
    PnLReport pnlreport = new PnLReport();

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

    public String generatePnL(){
        return pnlreport.generatePnL();
    }


    private void pushDb(dailyReport r){
        repo.updateDaily(r.getDate(), r.getDrinksSales(), r.getHotFoodSales(), r.getRegularFuelSales(), r.getSales(), r.getSnackSales(), r.getTaxCollected(), r.getTaxRate());
    }
}
