package com.kislun.testtask.model.report.date;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class SalesAndTrafficByDate {
    private LocalDate localDate;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
