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
import java.util.Date;
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

    public S3Object getSalesFile(String date) throws IOException {
        //Generate Monthly Report
        String bucket = "dailysalescollection/Sales";
        String fileToPull = date + ".csv";

        //System.out.println(fileToPull + " ?");
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

    public List<String> getSales(String date){
        return repo.getSales(date);
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
            //System.out.println(    Double.parseDouble(data[i]) + "  This is the data in the array");
        }
            pushSalesDb(data);


    }

    public List<String> getMonthlyReport(String month) {

        //String s = Integer.toString(month);
        //String s = month.toString();
       // System.out.println(s + "  am i here????");
        String mm = "" + month.charAt(0) + month.charAt(1);
        String yy = "" + month.charAt(2) + month.charAt(3);

        //for(int i = 0; i < s.length(); i++){
        //    System.out.println(s.charAt(i) + " index of the date !!!!!!!!!!!!!");
        //}


       // return repo.monthly(Integer.toString(mmdd));

        return repo.m(mm, yy);
    }

    //UNSURE OF RETURN TYPE
    public String pnLGenerate(String start, String end){
        updatePnl(start, end);
        String report = pnlreport.toString();
        pnlreport.clearReport();
        String output = "Profit and Loss for XYZ Inc from " + start + " to " + end;

        return output + report;
    }

    private void updatePnl(String s, String e){
        List<String> pnl = repo.p(s, e);
      //  System.out.println(pnl.size() + " HEYYYYYYYYYYYYYYYYY");
        for(String str: pnl){
    //        System.out.println(str + " HIIIIIIIIIIIIIIIIIII");
        }
        List<List<Double>> convertedData = convertData(pnl);

        for(int i = 0; i < convertedData.size(); i++){
            List<Double> temp = convertedData.get(i);
            pnlreport.setSales(temp.get(2));
            pnlreport.setTax(temp.get(3));
            //pnlreport.setQty(temp.get(2));
            pnlreport.setCogs(temp.get(0), temp.get(1));
        }
    }

    private List<List<Double>> convertData(List<String> monthly){
        List<List<Double>> convert = new ArrayList<>();
        List<Double> temp2;
        for(String s: monthly){
            String[] temp = s.split(",");
            System.out.println(temp.length + "  This is the length");
            for(String t : temp){System.out.println(t + " Content????");}
            temp2 = new ArrayList<>(temp.length);
         //   System.out.println(temp2.size() + "  This is the List length");
            for(int i = 1; i < temp.length; i++){
                Double d = Double.parseDouble(temp[i]);
                temp2.add(i-1,d);
            }
            convert.add(temp2);
          //  System.out.println(s + "   These are the numbers" );
        }
        return convert;
    }

    private void pushSalesDb(String[] data) {
        repo.updateDailySales(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]) ,Double.parseDouble(data[4]) );
    }



}
