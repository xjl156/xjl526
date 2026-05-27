package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.entity.Dish;
import com.restaurant.management.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishRepository dishRepository;

    @GetMapping("/store/{storeId}")
    public Result<List<Dish>> getByStore(@PathVariable Long storeId) {
        return Result.success(dishRepository.findByStoreIdAndStatus(storeId, 1));
    }

    @GetMapping("/{id}")
    public Result<Dish> get(@PathVariable Long id) {
        return Result.success(dishRepository.findById(id).orElse(null));
    }

    @PostMapping
    public Result<Dish> create(@RequestBody Dish dish) {
        return Result.success(dishRepository.save(dish));
    }

    @PutMapping("/{id}")
    public Result<Dish> update(@PathVariable Long id, @RequestBody Dish dish) {
        dish.setId(id);
        return Result.success(dishRepository.save(dish));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        dishRepository.deleteById(id);
        return Result.success(null);
    }

    @PutMapping("/{id}/toggle")
    public Result<Dish> toggle(@PathVariable Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow();
        dish.setStatus(dish.getStatus() == 1 ? 0 : 1);
        return Result.success(dishRepository.save(dish));
    }

    @GetMapping("/recommendations/{storeId}")
    public Result<List<Dish>> getRecommendations(@PathVariable Long storeId) {
        return Result.success(dishRepository.findByStoreIdOrderByPriorityScoreDesc(storeId));
    }
}
