package com.kislun.testtask;

import com.kislun.testtask.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataImportRunner implements CommandLineRunner {
    private final DataImportService dataImportService;

    @Autowired
    public DataImportRunner(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @Override
    public void run(String... args) throws Exception {
    String path = "test_report.json";
    dataImportService.importData(path);
    }

}
