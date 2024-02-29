package com.kislun.testtask.model.report.date;

import com.kislun.testtask.model.report.SalesAmount;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SalesByDate {
    private SalesAmount orderedProductSales;
    private SalesAmount orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private SalesAmount averageSalesPerOrderItem;
    private SalesAmount averageSalesPerOrderItemB2B;
    private Double averageUnitsPerOrderItem;
    private Double averageUnitsPerOrderItemB2B;
    private SalesAmount averageSellingPrice;
    private SalesAmount averageSellingPriceB2B;
    private Integer unitsRefunded;
    private Double refundRate;
    private Integer claimsGranted;
    private SalesAmount claimsAmount;
    private SalesAmount shippedProductSales;
    private Integer unitsShipped;
    private Integer ordersShipped;
}
