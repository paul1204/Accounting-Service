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
    public String a(){
        return "Welcome to the Accounting Service!";
    }


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
        this.accService = a;
    }

    @PostMapping(path = base + "/daily")
    //public void d(@RequestBody TreeMap<k, v> map){
    public void d(@RequestBody dailyReport r){
     //   dailyReport s = (dailyReport) map.get("dailyReport");
      //  service.reportDaily((dailyReport) map.get("dailyReport"));
        accService.reportDaily(r);
    }

    @GetMapping(path = base +"/sales")
    public String sales() throws IOException {
        //Generate Monthly Report
        String bucket = "dailysalescollection";
        AWSCredentials credentials = new BasicAWSCredentials(
                "Update Me!",
                "Update Me!"

        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        S3Object file = s3client.getObject(new GetObjectRequest(bucket, "Shift Report"));
        return displayTextInputStream(file.getObjectContent());

    }


    private static String displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            return gson.toJson(line);
            //System.out.println(line);

        }
        System.out.println();
        return null;
    }

    @GetMapping(path = base +"/monthly")
    public String m(){
        //Generate Monthly Report
        return "Hi";
    }

    @GetMapping(path = base+"/pnl")
    public String pnl(){
        return accService.generatePnL();
    }
}