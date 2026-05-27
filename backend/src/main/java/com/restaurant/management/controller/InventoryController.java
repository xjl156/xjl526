package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.entity.Inventory;
import com.restaurant.management.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @GetMapping("/store/{storeId}")
    public Result<List<Inventory>> getByStore(@PathVariable Long storeId) {
        return Result.success(inventoryRepository.findByStoreId(storeId));
    }
}
