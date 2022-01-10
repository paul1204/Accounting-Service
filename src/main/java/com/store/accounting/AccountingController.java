package com.store.accounting;



import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class AccountingController {
    @GetMapping("accounting")
    public String main(){
        return "Accounting Main Page";
    }

    @GetMapping("hello")
    public String test(){
        return "THIS WORKSSSSSS";
    }

    AccountingService s = new AccountingService();

    @PostMapping(path = "accounting/daily")
    public void d(@RequestBody dailyReport r){
        s.reportDaily(r);
    }

    @GetMapping(path = "accounting/monthly")
    public void m(@RequestBody dailyReport r){
        s.reportDaily(r);
    }
}
