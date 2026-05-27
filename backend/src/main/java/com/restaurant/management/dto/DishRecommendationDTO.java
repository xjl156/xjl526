package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DishRecommendationDTO {
    private List<RecommendItem> recommendItems;
    private List<OptimizeItem> optimizeItems;

    @Data
    public static class RecommendItem {
        private Long dishId;
        private String dishName;
        private BigDecimal costPrice;
        private BigDecimal profitMargin;
        private double turnoverRate;
        private double score;
    }

    @Data
    public static class OptimizeItem {
        private Long dishId;
        private String dishName;
        private BigDecimal costPrice;
        private BigDecimal profitMargin;
        private double turnoverRate;
        private double score;
        private String suggestion;
    }
}
