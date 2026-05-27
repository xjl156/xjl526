package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchasePredictionDTO {
    private Long ingredientId;
    private String ingredientName;
    private String unit;
    private BigDecimal predictedQuantity;
    private BigDecimal currentStock;
    private BigDecimal suggestedPurchase;
    private List<String> reasons;
}
