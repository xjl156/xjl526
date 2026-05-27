package com.restaurant.management.service;

import com.restaurant.management.dto.PurchasePredictionDTO;
import java.util.List;

public interface PurchasePredictionService {
    List<PurchasePredictionDTO> predict(Long storeId);
}
