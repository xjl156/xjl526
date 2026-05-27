package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProfitAnalysisDTO {
    private BigDecimal monthlyTarget;
    private BigDecimal monthlyAchieved;
    private double completionRate;
    private int remainingDays;
    private BigDecimal dailyRequired;
    private BigDecimal todayActual;
    private List<DailyProfitItem> dailyTrend;

    @Data
    public static class DailyProfitItem {
        private String date;
        private BigDecimal amount;
    }
}
