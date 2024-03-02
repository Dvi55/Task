package com.kislun.testtask.repo;

import com.kislun.testtask.model.report.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, String> {
}
