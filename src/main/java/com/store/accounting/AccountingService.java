package com.store.accounting;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountingService {

    dailyReport report;
    monthlyReport monthlyReport;
    private AccountingRepo repo;
    PnLReport pnlreport = new PnLReport();


    //@Value("${user}")
    //private String user;

    //@Value("${pass}")
    //private String pass;

    @Autowired
    public AccountingService(AccountingRepo r){
        this.repo = r;
        //this.user = user;
    }

    @Transactional
    public void reportDaily(dailyReport r){
        report = new dailyReport(r);
       // pushDb(report);
        System.out.println(report.toString());
        //Add to Database
    }

    public S3Object getSales(String date) throws IOException {
        //Generate Monthly Report
        String bucket = "dailysalescollection/Sales";
        String fileToPull = date + ".csv";
     //   s3://dailysalescollection/Sales/010122.csv
        AWSCredentials credentials = new BasicAWSCredentials(
                "",
                ""
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        S3Object file = s3client.getObject(new GetObjectRequest(bucket, fileToPull));
        return file;
    }

    public String convertData(S3Object salesFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(salesFile.getObjectContent()));
        String salesData = reader.readLine();
        return salesData;
    }

    @Transactional
    public void updateDB(String sales) throws IOException {
        String[] data = sales.split(",");
        for (int i = 0; i < data.length; i++) {
            //To view in console
            System.out.println(    Double.parseDouble(data[i]) + "  This is the data in the array");
        }
            pushSalesDb(data);
    }

    public List<String> getMonthlyReport(Integer mmdd) {
        return repo.monthly(mmdd);
    }

    //UNSURE OF RETURN TYPE
    public String pnLGenerate(Integer start, Integer end){
        updatePnl(start, end);
        String report = pnlreport.toString();
        pnlreport.clearReport();
        return report;
    }

    private void updatePnl(Integer s, Integer e){
        List<String> pnl = repo.pnl(s, e);
        List<List<Double>> convertedData = convertData(pnl);

        for(int i = 0; i < convertedData.size(); i++){
            List<Double> temp = convertedData.get(i);
            pnlreport.setSales(temp.get(0));
            pnlreport.setTax(temp.get(1));
            //pnlreport.setQty(temp.get(2));
            pnlreport.setCogs(temp.get(3), temp.get(2));
        }
    }

    private List<List<Double>> convertData(List<String> monthly){
        List<List<Double>> convert = new ArrayList<>();
        List<Double> temp2;
        for(String s: monthly){
            String[] temp = s.split(",");
         //   System.out.println(temp.length + "  This is the length");
            temp2 = new ArrayList<>(temp.length);
         //   System.out.println(temp2.size() + "  This is the List length");
            for(int i = 0; i < temp.length; i++){
                Double d = Double.parseDouble(temp[i]);
                temp2.add(i,d);
            }
            convert.add(temp2);
          //  System.out.println(s + "   These are the numbers" );
        }
        return convert;
    }


    private void pushSalesDb(String[] data) {
        repo.updateDailySales(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }
}
