package com.kislun.testtask.model.report;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private LocalDate dataStartTime;
    private LocalDate dataEndTime;
    private List<String> marketplaceIds;
}
