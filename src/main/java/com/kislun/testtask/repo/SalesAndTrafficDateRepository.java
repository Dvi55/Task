package com.kislun.testtask.repo;

import com.kislun.testtask.model.report.date.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesAndTrafficDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {
    List<SalesAndTrafficByDate> findByDate(Date date);

    List<SalesAndTrafficByDate> findByDateBetween(Date startDate, Date endDate);


}
