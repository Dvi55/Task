package com.kislun.testtask.model.report;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SalesAmount {
    private Double amount;
    private String currencyCode;
}
