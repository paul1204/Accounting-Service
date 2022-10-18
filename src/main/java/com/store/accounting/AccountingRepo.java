package com.store.accounting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountingRepo extends JpaRepository<dailyReport, Double> {

    @Modifying
    @Query(value = "INSERT into sales_report " +
            "(date , sales, tax_collected, qty, cogs) values (:date ,:sales, :tax_collected, :qty, :cogs)", nativeQuery = true)
    void updateDailySales(
                     @Param("date") String date,
                     @Param("sales") Double sales,
                     @Param("tax_collected") Double tax_collected,
                     @Param("qty") Double qty,
                     @Param("cogs") Double cogs
    );

    @Query(value = "SELECT * from daily_sales", nativeQuery = true)
    List<String> monthly(@Param("mmyy") String mmyy);

    @Query(value = "SELECT * from daily_sales", nativeQuery = true)
    List<String> pnl(@Param("start") Integer start , @Param("end") Integer end );


    @Query(value = "SELECT * from sales_report s_r where s_r.date = :date", nativeQuery = true)
    List<String> getSales(@Param("date") String date);


    @Query(value = "SELECT * from sales_report s_r where SUBSTRING(s_r.date, 1,2) = :mm AND SUBSTRING(s_r.dat,2) = :yy", nativeQuery = true)
    List<String> m(@Param("mm") String mm, @Param("yy") String yy);


}


//    @Query(value = "SELECT * from sales_report s_r where SUBSTRING(s_r.date, 1,2) = :mm", nativeQuery = true)
//    List<String> monthly(@Param("mm") String mm, @Param("yy") String yy );