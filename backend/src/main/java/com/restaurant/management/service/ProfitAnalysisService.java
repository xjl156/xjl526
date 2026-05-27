package com.restaurant.management.service;

import com.restaurant.management.dto.ProfitAnalysisDTO;

public interface ProfitAnalysisService {
    ProfitAnalysisDTO analyze(Long storeId);
}
