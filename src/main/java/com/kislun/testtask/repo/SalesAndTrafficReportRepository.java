package com.kislun.testtask.repo;

import com.kislun.testtask.model.report.SalesAndTrafficReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface SalesAndTrafficReportRepository extends MongoRepository<SalesAndTrafficReport, String> {


}
