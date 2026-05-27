package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.dto.DashboardDTO;
import com.restaurant.management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DishOrderRepository orderRepository;
    private final SalesRecordRepository salesRecordRepository;
    private final InventoryRepository inventoryRepository;

    @GetMapping("/store/{storeId}")
    public Result<DashboardDTO> dashboard(@PathVariable Long storeId) {
        LocalDate today = LocalDate.now();

        DashboardDTO dto = new DashboardDTO();

        var todayRecord = salesRecordRepository.findByStoreIdAndBusinessDate(storeId, today);
        if (todayRecord.isPresent()) {
            dto.setTodayCustomers(todayRecord.get().getTotalCustomers());
            dto.setTodayRevenue(todayRecord.get().getTotalAmount());
        }

        long pending = orderRepository.findByStoreIdAndStatus(storeId, "PENDING").size();
        dto.setPendingOrders((int) pending);

        long lowStock = inventoryRepository.findByStoreId(storeId).stream()
                .filter(i -> i.getQuantity().compareTo(BigDecimal.TEN) < 0)
                .count();
        dto.setLowStockItems((int) lowStock);

        var weekStart = today.minusDays(6);
        var weekly = salesRecordRepository.findByStoreIdAndBusinessDateBetween(storeId, weekStart, today);
        dto.setWeeklyRevenue(weekly.stream().map(r -> {
            DashboardDTO.DailyRevenue dr = new DashboardDTO.DailyRevenue();
            dr.setDate(r.getBusinessDate().toString());
            dr.setAmount(r.getTotalAmount());
            return dr;
        }).collect(Collectors.toList()));

        return Result.success(dto);
    }
}
