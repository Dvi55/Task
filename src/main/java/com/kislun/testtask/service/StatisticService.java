package com.kislun.testtask.service;

import com.kislun.testtask.model.report.asin.SalesAndTrafficByAsin;
import com.kislun.testtask.model.report.date.SalesAndTrafficByDate;
import com.kislun.testtask.repo.SalesAndTrafficAsinRepository;
import com.kislun.testtask.repo.SalesAndTrafficDateRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StatisticService {

    private final SalesAndTrafficAsinRepository salesAndTrafficAsinRepository;
    private final SalesAndTrafficDateRepository salesAndTrafficDateRepository;


    public StatisticService(SalesAndTrafficAsinRepository salesAndTrafficAsinRepository, SalesAndTrafficDateRepository salesAndTrafficDateRepository) {
        this.salesAndTrafficAsinRepository = salesAndTrafficAsinRepository;
        this.salesAndTrafficDateRepository = salesAndTrafficDateRepository;
    }

    public static String getSortedAsinsKey(List<String> asins) {
        Collections.sort(asins);
        return asins.toString();
    }

    @Cacheable(value = "dateRangeCache", key = "#date.toString() + #endDate.toString()")
    public List<SalesAndTrafficByDate> getStatisticByDateRange(Date date, Date endDate) {
        return salesAndTrafficDateRepository.findByDateBetween(date, endDate);

    }

    @Cacheable(value = "dateCache", key = "#date")
    public List<SalesAndTrafficByDate> getStatisticByDate(Date date) {

        return salesAndTrafficDateRepository.findByDate(date);
    }

    @Cacheable(value = "asinCache", key = "T(com.kislun.testtask.service.StatisticService).getSortedAsinsKey(#asins)")
    public List<SalesAndTrafficByAsin> getStatisticByAsin(List<String> asins) {
        Collections.sort(asins);
        return salesAndTrafficAsinRepository.findByParentAsinIn(asins);
    }
}
