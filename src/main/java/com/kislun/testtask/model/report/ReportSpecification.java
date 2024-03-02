package com.kislun.testtask.model.report;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "reportSpecification")
public class ReportSpecification {
    @Id
    private String id;
    private String reportType;
    private ReportOptions reportOptions;
    private Date dataStartTime;
    private Date dataEndTime;
    private List<String> marketplaceIds;
}
