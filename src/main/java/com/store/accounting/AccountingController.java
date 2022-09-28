package com.store.accounting;



import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.store.products.Drink;
import com.store.products.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

//import java.sql.Date;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
class AccountingController <k,v>{

    static Gson gson = new Gson();


    AccountingService accService;
    final String base = "accounting";

    @GetMapping("/")
    public String home(){
        return "Welcome!\n " +
                " - To access the Accounting Service, use /accounting";
    }


    @GetMapping(base)
    public String main(){
        return "Accounting Main Page\n" +
                " - Use /sales/{date}  to view sales from a certain date\n" +
                " - Example\n" +
                " - http://localhost:8080/accounting/sales/010122"
                ;
    }

    @Autowired
    public AccountingController(AccountingService a){
        this.accService = a;
    }

    @PostMapping(path = base + "/daily")
    public void d(@RequestBody dailyReport r){
        accService.reportDaily(r);
    }


    //date formating is MM/DD/YY
    @GetMapping(path = base +"/sales/view/{date}")
    public String salesView(@PathVariable("date") String date) throws IOException {
        S3Object salesFile = accService.getSales(date);
        return accService.convertData(salesFile);
    }

    //date formating is MM/DD/YY
    @PostMapping(path = base +"/sales/post/{date}")
    public void salesPost(@PathVariable("date") String date) throws IOException {
        S3Object salesFile = accService.getSales(date);
        String salesData = accService.convertData(salesFile);
        accService.updateDB(salesData);
    }

    @GetMapping(path = base +"/monthly")
    public List<String> m(){
        return accService.getMonthlyReport();
    }

    @GetMapping(path = base+"/pnl")
    public String pnl(){
        return accService.pnLGenerate();
    }

}