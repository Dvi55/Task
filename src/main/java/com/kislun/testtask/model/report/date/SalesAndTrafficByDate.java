package com.kislun.testtask.model.report.date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection = "salesAndTrafficByDate")
public class SalesAndTrafficByDate {
    @Id
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
