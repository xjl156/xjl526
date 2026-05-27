package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.dto.ProfitAnalysisDTO;
import com.restaurant.management.service.ProfitAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ProfitAnalysisService profitAnalysisService;

    @GetMapping("/profit/{storeId}")
    public Result<ProfitAnalysisDTO> profit(@PathVariable Long storeId) {
        return Result.success(profitAnalysisService.analyze(storeId));
    }
}
