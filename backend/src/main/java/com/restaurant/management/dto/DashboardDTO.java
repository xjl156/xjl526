package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DashboardDTO {
    private int todayCustomers;
    private BigDecimal todayRevenue;
    private int pendingOrders;
    private int lowStockItems;
    private List<HotDish> hotDishes;
    private List<DailyRevenue> weeklyRevenue;

    @Data
    public static class HotDish {
        private Long id;
        private String name;
        private int salesCount;
    }

    @Data
    public static class DailyRevenue {
        private String date;
        private BigDecimal amount;
    }
}
