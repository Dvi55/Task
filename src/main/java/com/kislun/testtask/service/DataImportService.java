package com.kislun.testtask.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kislun.testtask.model.report.SalesAndTrafficReport;
import com.kislun.testtask.repo.SalesAndTrafficReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DataImportService {
    private final SalesAndTrafficReportRepository salesAndTrafficReportRepository;

    @Autowired
    public DataImportService(SalesAndTrafficReportRepository salesAndTrafficReportRepository) {
        this.salesAndTrafficReportRepository = salesAndTrafficReportRepository;
    }

    public void importData(String pathFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            SalesAndTrafficReport report = objectMapper.readValue(new File(pathFile), SalesAndTrafficReport.class);
            salesAndTrafficReportRepository.save(report);
            System.out.println("Data imported");
        } catch (IOException e) {
            System.out.println("Error import data");
            e.printStackTrace();
        }
    }

    public void updateData(String pathFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            SalesAndTrafficReport report = objectMapper.readValue(new File(pathFile), SalesAndTrafficReport.class);
            salesAndTrafficReportRepository.deleteAll();
            System.out.println("Data updated");
        } catch (IOException e) {
            System.out.println("Error import data");
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void updateDataFromJsonFile() {
        String pathFile = "test_report.json";
        updateData(pathFile);
    }
}
