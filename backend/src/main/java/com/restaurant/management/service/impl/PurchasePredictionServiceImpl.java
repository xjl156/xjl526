package com.restaurant.management.service.impl;

import com.restaurant.management.dto.PurchasePredictionDTO;
import com.restaurant.management.repository.*;
import com.restaurant.management.service.PurchasePredictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchasePredictionServiceImpl implements PurchasePredictionService {

    @Override
    public List<PurchasePredictionDTO> predict(Long storeId) {
        log.info("采购预测: storeId={}", storeId);
        return Collections.emptyList();
    }
}
