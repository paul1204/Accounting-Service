package com.store.accounting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRepo extends JpaRepository<dailyReport, Double> {

//    @Modifying
//    @Query(value = "INSERT into daily_report " +
//            "(date, drinks_sales, hot_food_sales, regular_fuel_sales, sales, snack_sales, tax_collected, tax_rate) values (:date, :drink_sales, :hot_food_sales, :regular_fuel_sales, :sales, :snack_sales, :tax_collected, :tax_rate)", nativeQuery = true)
//    void updateDaily(@Param("date") String date, @Param("drink_sales") Double drink_sales,
//                     @Param("hot_food_sales") Double hot_food_sales,
//                     @Param("regular_fuel_sales") Double regular_fuel_sales,
//                     @Param("sales") Double sales,
//                     @Param("snack_sales") Double snack_sales,
//                     @Param("tax_collected") Double tax_collected,
//                     @Param("tax_rate") Double tax_rate
//                     );

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

}

//CREATE TABLE daily_sales( total_sales float(2), tax_collected float(2), quantitySold int, cogs float(2) );
