package com.kislun.testtask.controller;

import com.kislun.testtask.aggregation.TotalStatsAggregation;
import com.kislun.testtask.model.report.asin.SalesAndTrafficByAsin;
import com.kislun.testtask.model.report.date.SalesAndTrafficByDate;
import com.kislun.testtask.service.StatisticService;
import org.bson.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {

    private final StatisticService statisticService;
    private final TotalStatsAggregation totalStatsAggregation;

    public StatisticController(StatisticService statisticService, TotalStatsAggregation totalStatsAggregation) {
        this.statisticService = statisticService;
        this.totalStatsAggregation = totalStatsAggregation;
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<SalesAndTrafficByDate>> getStatisticByDate(@PathVariable() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return new ResponseEntity<>(statisticService.getStatisticByDate(date), HttpStatus.OK);
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public ResponseEntity<List<SalesAndTrafficByDate>> getStatisticByDateRange(@PathVariable() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                               @PathVariable() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return new ResponseEntity<>(statisticService.getStatisticByDateRange(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/asin/{asins}")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getStatisticByAsin(@PathVariable() List<String> asins) {
        return ResponseEntity.ok(statisticService.getStatisticByAsin(asins));
    }

    @GetMapping("/total/{type}")
    public ResponseEntity<Document> getTotalStats(@PathVariable() String type) {
        if (type.equals("date"))
            return ResponseEntity.ok(totalStatsAggregation.getTotalByDate());
        else if (type.equals("asin"))
            return ResponseEntity.ok(totalStatsAggregation.getTotalByAsin());
        else
            return ResponseEntity.badRequest().build();
    }

}



