package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.dto.PurchasePredictionDTO;
import com.restaurant.management.service.PurchasePredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchasePredictionService purchasePredictionService;

    @GetMapping("/predict/{storeId}")
    public Result<List<PurchasePredictionDTO>> predict(@PathVariable Long storeId) {
        return Result.success(purchasePredictionService.predict(storeId));
    }
}
