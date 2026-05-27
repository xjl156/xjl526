package com.restaurant.management.service.impl;

import com.restaurant.management.dto.ProfitAnalysisDTO;
import com.restaurant.management.repository.*;
import com.restaurant.management.service.ProfitAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfitAnalysisServiceImpl implements ProfitAnalysisService {

    @Override
    public ProfitAnalysisDTO analyze(Long storeId) {
        log.info("盈利分析: storeId={}", storeId);
        ProfitAnalysisDTO dto = new ProfitAnalysisDTO();
        dto.setMonthlyTarget(new BigDecimal("150000"));
        dto.setMonthlyAchieved(BigDecimal.ZERO);
        dto.setCompletionRate(0);
        dto.setRemainingDays(30);
        dto.setDailyRequired(new BigDecimal("5000"));
        dto.setTodayActual(BigDecimal.ZERO);
        dto.setDailyTrend(Collections.emptyList());
        return dto;
    }
}
