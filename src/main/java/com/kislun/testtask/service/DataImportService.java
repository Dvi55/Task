package com.kislun.testtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kislun.testtask.model.report.ReportSpecification;
import com.kislun.testtask.model.report.SalesAndTrafficReport;
import com.kislun.testtask.model.report.asin.SalesAndTrafficByAsin;
import com.kislun.testtask.model.report.date.SalesAndTrafficByDate;
import com.kislun.testtask.repo.ReportSpecificationRepository;
import com.kislun.testtask.repo.SalesAndTrafficAsinRepository;
import com.kislun.testtask.repo.SalesAndTrafficDateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DataImportService {

    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficAsinRepository salesAndTrafficAsinRepository;
    private final SalesAndTrafficDateRepository salesAndTrafficDateRepository;

    public DataImportService(ReportSpecificationRepository reportSpecificationRepository, SalesAndTrafficAsinRepository salesAndTrafficAsinRepository, SalesAndTrafficDateRepository salesAndTrafficDateRepository) {
        this.reportSpecificationRepository = reportSpecificationRepository;
        this.salesAndTrafficAsinRepository = salesAndTrafficAsinRepository;
        this.salesAndTrafficDateRepository = salesAndTrafficDateRepository;
    }

    @CacheEvict(value = {"asinCache", "totalByAsinCache"}, allEntries = true)
    public void importData(String pathFile) {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        reportSpecificationRepository.deleteAll();
        salesAndTrafficAsinRepository.deleteAll();
        salesAndTrafficDateRepository.deleteAll();

        try {
            SalesAndTrafficReport report = objectMapper.readValue(new File(pathFile), SalesAndTrafficReport.class);
            ReportSpecification reportSpecification = report.getReportSpecification();
            List<SalesAndTrafficByDate> salesAndTrafficByDates = report.getSalesAndTrafficByDate();
            List<SalesAndTrafficByAsin> salesAndTrafficByAsins = report.getSalesAndTrafficByAsin();
            reportSpecificationRepository.save(reportSpecification);
            salesAndTrafficAsinRepository.saveAll(salesAndTrafficByAsins);
            salesAndTrafficDateRepository.saveAll(salesAndTrafficByDates);
           log.info("Data imported");
        } catch (IOException e) {
            log.info("Error import data");
            e.printStackTrace();
        }
    }

    @CacheEvict(value = {"asinCache", "totalByAsinCache"}, allEntries = true)
    @Scheduled(initialDelay = 10 * 1000, fixedDelay = 10 * 60 * 1000)
    public void updateDataFromJsonFile() {
        String pathFile = "test_report.json";
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        List<ReportSpecification> reportSpecificationList = reportSpecificationRepository.findAll();
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = salesAndTrafficAsinRepository.findAll();
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = salesAndTrafficDateRepository.findAll();
        try {
            SalesAndTrafficReport report = objectMapper.readValue(new File(pathFile), SalesAndTrafficReport.class);
            reportSpecificationRepository.save(report.getReportSpecification());
            salesAndTrafficAsinRepository.saveAll(report.getSalesAndTrafficByAsin());
            salesAndTrafficDateRepository.saveAll(report.getSalesAndTrafficByDate());
            log.info("Data updated");
        } catch (IOException e) {
            log.info("Error update data");
            e.printStackTrace();
        }
        reportSpecificationRepository.deleteAll(reportSpecificationList);
        salesAndTrafficAsinRepository.deleteAll(salesAndTrafficByAsinList);
        salesAndTrafficDateRepository.deleteAll(salesAndTrafficByDateList);
    }
}

