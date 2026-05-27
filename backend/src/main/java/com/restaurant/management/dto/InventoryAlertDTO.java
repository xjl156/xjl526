package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class InventoryAlertDTO {
    private List<AlertItem> alerts;

    @Data
    public static class AlertItem {
        private Long ingredientId;
        private String ingredientName;
        private String unit;
        private BigDecimal currentStock;
        private BigDecimal safetyStock;
        private String alertType;
        private int daysToExpiry;
    }
}
