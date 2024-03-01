package com.kislun.testtask.model.report;

import com.kislun.testtask.model.report.asin.SalesAndTrafficByAsin;
import com.kislun.testtask.model.report.date.SalesAndTrafficByDate;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class SalesAndTrafficReport {
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
