package com.store.accounting;



import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "accounting")
class AccountingController {

    @GetMapping
    public String get(){
        return "Accounting Main Page";
    }


}

@RestController
@RequestMapping(path = "accounting/sales")
class dailySales {

    AccountingService s = new AccountingService();

    @PostMapping
    public void d(@RequestBody dailyReport r){
                s.reportDaily(r);
    }

   // @GetMapping
   // public String get(){
   // }
}