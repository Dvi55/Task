package com.kislun.testtask.model.report.asin;

import com.kislun.testtask.model.report.SalesAmount;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SalesByAsin {
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private SalesAmount orderedProductSales;
    private SalesAmount orderedProductSalesB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
}
