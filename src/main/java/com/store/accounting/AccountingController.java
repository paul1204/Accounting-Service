package com.store.accounting;



import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
class AccountingController <k,v>{

    AccountingService service;
    final String base = "accounting";

    @GetMapping(base)
    public String main(){
        return "Accounting Main Page";
    }

    @GetMapping(base + "/hello")
    public String test(){
        return "THIS WORKSSSSSS";
    }

    @Autowired
    public AccountingController(AccountingService a){
        this.service = a;
    }

    @PostMapping(path = base + "/daily")
    //public void d(@RequestBody TreeMap<k, v> map){
    public void d(@RequestBody dailyReport r){
     //   dailyReport s = (dailyReport) map.get("dailyReport");
      //  service.reportDaily((dailyReport) map.get("dailyReport"));
     service.reportDaily(r);
    }

    @GetMapping(path = base +"/monthly")
    public void m(){
        //Generate Monthly Report
    }

    @GetMapping(path = base+"/pnl")
    public String pnl(){
        return service.generatePnL();
    }
}