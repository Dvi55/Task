package com.kislun.testtask.model.report.date;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class SalesAndTrafficByDate {
    private Date date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
