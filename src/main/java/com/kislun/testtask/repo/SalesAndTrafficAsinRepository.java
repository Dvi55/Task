package com.kislun.testtask.repo;

import com.kislun.testtask.model.report.asin.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesAndTrafficAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
