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
    @Query(value = "INSERT into daily_sales " +
            "(total_Sales, tax_collected, quantitySold, cogs) values (:total_Sales, :tax_collected, :quantitySold, :cogs)", nativeQuery = true)
    void updateDailySales(
                     //@Param("date") String date,
                     @Param("total_Sales") Double total_sales,
                     @Param("tax_collected") Double tax_collected,
                     @Param("quantitySold") Double quantitySold,
                     @Param("cogs") Double cogs
    );

    @Query(value = "SELECT * from daily_sales", nativeQuery = true)
    List<String> m();
}
