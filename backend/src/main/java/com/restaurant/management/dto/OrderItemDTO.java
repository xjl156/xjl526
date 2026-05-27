package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
