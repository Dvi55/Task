package com.kislun.testtask.aggregation;

import org.bson.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.stereotype.Service;


@Service
public class TotalStatsAggregation {

    final MongoTemplate mongoTemplate;

    public TotalStatsAggregation(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Cacheable("totalByAsinCache")
    public Document getTotalByAsin() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group(Fields.from())
                        .sum("salesByAsin.unitsOrdered").as("unitsOrdered")
                        .sum("salesByAsin.unitsOrderedB2B").as("unitsOrderedB2B")
                        .sum("salesByAsin.orderedProductSales.amount").as("orderedProductSalesAmount")
                        .sum("salesByAsin.orderedProductSalesB2B.amount").as("orderedProductSalesB2BAmount")
                        .sum("salesByAsin.totalOrderItems").as("totalOrderItems")
                        .sum("salesByAsin.totalOrderItemsB2B").as("totalOrderItemsB2B")
                        .sum("trafficByAsin.browserSessions").as("browserSessions")
                        .sum("trafficByAsin.browserSessionsB2B").as("browserSessionsB2B")
                        .sum("trafficByAsin.mobileAppSessions").as("mobileAppSessions")
                        .sum("trafficByAsin.mobileAppSessionsB2B").as("mobileAppSessionsB2B")
                        .sum("trafficByAsin.sessions").as("sessions")
                        .sum("trafficByAsin.sessionsB2B").as("sessionsB2B")
                        .sum("trafficByAsin.browserSessionPercentage").as("browserSessionPercentage")
                        .sum("trafficByAsin.browserSessionPercentageB2B").as("browserSessionPercentageB2B")
                        .sum("trafficByAsin.mobileAppSessionPercentage").as("mobileAppSessionPercentage")
                        .sum("trafficByAsin.mobileAppSessionPercentageB2B").as("mobileAppSessionPercentageB2B")
                        .sum("trafficByAsin.sessionPercentage").as("sessionPercentage")
                        .sum("trafficByAsin.sessionPercentageB2B").as("sessionPercentageB2B")
                        .sum("trafficByAsin.browserPageViews").as("browserPageViews")
                        .sum("trafficByAsin.browserPageViewsB2B").as("browserPageViewsB2B")
                        .sum("trafficByAsin.mobileAppPageViews").as("mobileAppPageViews")
                        .sum("trafficByAsin.mobileAppPageViewsB2B").as("mobileAppPageViewsB2B")
                        .sum("trafficByAsin.pageViews").as("pageViews")
                        .sum("trafficByAsin.pageViewsB2B").as("pageViewsB2B")
                        .sum("trafficByAsin.browserPageViewsPercentage").as("browserPageViewsPercentage")
                        .sum("trafficByAsin.browserPageViewsPercentageB2B").as("browserPageViewsPercentageB2B")
                        .sum("trafficByAsin.mobileAppPageViewsPercentage").as("mobileAppPageViewsPercentage")
                        .sum("trafficByAsin.mobileAppPageViewsPercentageB2B").as("mobileAppPageViewsPercentageB2B")
                        .sum("trafficByAsin.pageViewsPercentage").as("pageViewsPercentage")
                        .sum("trafficByAsin.pageViewsPercentageB2B").as("pageViewsPercentageB2B")
                        .sum("trafficByAsin.buyBoxPercentage").as("buyBoxPercentage")
                        .sum("trafficByAsin.buyBoxPercentageB2B").as("buyBoxPercentageB2B")
                        .sum("trafficByAsin.unitSessionPercentage").as("unitSessionPercentage")
                        .sum("trafficByAsin.unitSessionPercentageB2B").as("unitSessionPercentageB2B")
                        .count().as("count")
        );

        return mongoTemplate.aggregate(aggregation, "salesAndTrafficByAsin", Document.class).getUniqueMappedResult();
    }

    @Cacheable("totalByDateCache")
    public Document getTotalByDate() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group(Fields.from())
                        .sum("salesByDate.unitsOrdered").as("unitsOrdered")
                        .sum("salesByDate.unitsOrderedB2B").as("unitsOrderedB2B")
                        .sum("salesByDate.orderedProductSales.amount").as("orderedProductSalesAmount")
                        .sum("salesByDate.orderedProductSalesB2B.amount").as("orderedProductSalesB2BAmount")
                        .sum("salesByDate.totalOrderItems").as("totalOrderItems")
                        .sum("salesByDate.totalOrderItemsB2B").as("totalOrderItemsB2B")
                        .sum("salesByDate.averageSalesPerOrderItem.amount").as("averageSalesPerOrderItemAmount")
                        .sum("salesByDate.averageSalesPerOrderItemB2B.amount").as("averageSalesPerOrderItemB2BAmount")
                        .sum("salesByDate.averageUnitsPerOrderItem").as("averageUnitsPerOrderItem")
                        .sum("salesByDate.averageUnitsPerOrderItemB2B").as("averageUnitsPerOrderItemB2B")
                        .sum("salesByDate.averageSellingPrice.amount").as("averageSellingPriceAmount")
                        .sum("salesByDate.averageSellingPriceB2B.amount").as("averageSellingPriceB2BAmount")
                        .sum("salesByDate.unitsRefunded").as("unitsRefunded")
                        .sum("salesByDate.refundRate").as("refundRate")
                        .sum("salesByDate.claimsGranted").as("claimsGranted")
                        .sum("salesByDate.claimsAmount.amount").as("claimsAmountAmount")
                        .sum("salesByDate.shippedProductSales.amount").as("shippedProductSalesAmount")
                        .sum("salesByDate.unitsShipped").as("unitsShipped")
                        .sum("salesByDate.ordersShipped").as("ordersShipped")
                        .sum("trafficByDate.browserPageViews").as("browserPageViews")
                        .sum("trafficByDate.browserPageViewsB2B").as("browserPageViewsB2B")
                        .sum("trafficByDate.mobileAppPageViews").as("mobileAppPageViews")
                        .sum("trafficByDate.mobileAppPageViewsB2B").as("mobileAppPageViewsB2B")
                        .sum("trafficByDate.pageViews").as("pageViews")
                        .sum("trafficByDate.pageViewsB2B").as("pageViewsB2B")
                        .sum("trafficByDate.browserSessions").as("browserSessions")
                        .sum("trafficByDate.browserSessionsB2B").as("browserSessionsB2B")
                        .sum("trafficByDate.mobileAppSessions").as("mobileAppSessions")
                        .sum("trafficByDate.mobileAppSessionsB2B").as("mobileAppSessionsB2B")
                        .sum("trafficByDate.sessions").as("sessions")
                        .sum("trafficByDate.sessionsB2B").as("sessionsB2B")
                        .sum("trafficByDate.buyBoxPercentage").as("buyBoxPercentage")
                        .sum("trafficByDate.buyBoxPercentageB2B").as("buyBoxPercentageB2B")
                        .sum("trafficByDate.orderItemSessionPercentage").as("orderItemSessionPercentage")
                        .sum("trafficByDate.orderItemSessionPercentageB2B").as("orderItemSessionPercentageB2B")
                        .sum("trafficByDate.unitSessionPercentage").as("unitSessionPercentage")
                        .sum("trafficByDate.unitSessionPercentageB2B").as("unitSessionPercentageB2B")
                        .sum("trafficByDate.averageOfferCount").as("averageOfferCount")
                        .sum("trafficByDate.averageParentItems").as("averageParentItems")
                        .sum("trafficByDate.feedbackReceived").as("feedbackReceived")
                        .sum("trafficByDate.negativeFeedbackReceived").as("negativeFeedbackReceived")
                        .sum("trafficByDate.receivedNegativeFeedbackRate").as("receivedNegativeFeedbackRate")
                        .count().as("count")
        );
        return mongoTemplate.aggregate(aggregation, "salesAndTrafficByDate", Document.class).getUniqueMappedResult();
    }
}

