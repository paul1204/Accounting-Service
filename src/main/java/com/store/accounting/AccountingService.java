package com.store.accounting;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.store.accounting.AccountingController.gson;


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



    public String generatePnL(){
        return pnlreport.generatePnL();
    }

    public S3Object getSales(String date) throws IOException {
        //Generate Monthly Report
        String bucket = "dailysalescollection/Sales";

        String fileToPull = date + ".csv";


     //   s3://dailysalescollection/Sales/010122.csv

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAVPZ34GDEVVMAH3F4",
                "VMsxeOwf+TBRWI/2rSNNVEK3hmk9GBy9Gbi9UY+X"

        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        S3Object file = s3client.getObject(new GetObjectRequest(bucket, fileToPull));


        return file;

    }

    @Transactional
    public String readSales(S3Object salesFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(salesFile.getObjectContent()));
        String salesData = reader.readLine();
        String[] data =  salesData.split(",");

        for (int i = 0; i < data.length; i++) {

            System.out.println(    Double.parseDouble(data[i]) + "  This is the data in the array");
        }

            pushSalesDb(data);
            return salesData;
    }

    public void getMonthlyReport() {

    }




    //Add time and date of these reports
    private void pushDb(dailyReport r){
        //repo.updateDaily(r.getDate(), r.getDrinksSales(), r.getHotFoodSales(), r.getRegularFuelSales(), r.getSales(), r.getSnackSales(), r.getTaxCollected(), r.getTaxRate());
    }

    private void pushSalesDb(String[] data) {
        repo.updateDailySales(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }
}
