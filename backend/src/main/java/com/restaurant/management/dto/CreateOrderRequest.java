package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderRequest {
    private Long storeId;
    private String tableNo;
    private Integer guestCount;
    private String remark;
    private List<OrderDishItem> items;

    @Data
    public static class OrderDishItem {
        private Long dishId;
        private String dishName;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}
