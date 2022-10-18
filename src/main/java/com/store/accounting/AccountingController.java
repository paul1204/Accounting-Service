package com.store.accounting;


import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@RestController
class AccountingController <k,v>{
    static Gson gson = new Gson();

    AccountingService accService;
    final String base = "accounting";

    @GetMapping("/")
    public String home(){
        return "Welcome!\n " +
                " - To access the Accounting Service, use /accounting to access the Accounting Service";
    }

    @GetMapping(base)
    public String main(){
        return "Accounting Main Page\n" +
                " - Use /monthly  to view sales from the month\n" +
                " - Example\n" +
                " - http://localhost:8080/accounting/monthly"
                ;
    }

    @Inject
    public AccountingController(AccountingService a){
        this.accService = a;
    }

    @PostMapping(path = base + "/daily")
    public void d(@RequestBody dailyReport r){
        accService.reportDaily(r);
    }

    //date formating is MM-DD-YY
    @GetMapping(path = base +"/sales/{date}")
    public List<String> salesView(@PathVariable("date") String date) throws IOException {
        return accService.getSales(date);
    }

    //date formating is MM/DD/YY
    @PutMapping(path = base +"/sales/{date}")
    public void salesPost(@PathVariable("date") String date) throws IOException {
        S3Object salesFile = accService.getSalesFile(date);
        String salesData = accService.convertData(salesFile);
        accService.updateDB(salesData);
    }

    @GetMapping(path = base +"/monthly/{mmyy}")
    public List<String> m(@PathVariable("mmyy") Integer month){
        return accService.getMonthlyReport(month);
    }

    @GetMapping(path = base+"/pnl/{mmyyS}-{mmyyE}")
    public String pnl(@PathVariable("mmyyS") Integer start , @PathVariable("mmyyE") Integer end){
        return "Yeah this works!";
        //return accService.pnLGenerate(start, end);
    }
}