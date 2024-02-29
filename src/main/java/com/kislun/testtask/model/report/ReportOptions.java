package com.kislun.testtask.model.report;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ReportOptions {
    private String dateGranularity;
    private String asinGranularity;
}
