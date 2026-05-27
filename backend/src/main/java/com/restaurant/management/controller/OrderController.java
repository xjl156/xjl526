package com.restaurant.management.controller;

import com.restaurant.management.common.Result;
import com.restaurant.management.dto.*;
import com.restaurant.management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Result<OrderDTO> create(@Valid @RequestBody CreateOrderRequest request) {
        return Result.success(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public Result<OrderDTO> get(@PathVariable Long id) {
        return Result.success(orderService.getOrder(id));
    }

    @GetMapping("/no/{orderNo}")
    public Result<OrderDTO> getByNo(@PathVariable String orderNo) {
        return Result.success(orderService.getOrderByNo(orderNo));
    }

    @GetMapping("/store/{storeId}")
    public Result<List<OrderDTO>> getByStore(@PathVariable Long storeId) {
        return Result.success(orderService.getOrdersByStore(storeId));
    }

    @PutMapping("/{id}/pay")
    public Result<OrderDTO> pay(@PathVariable Long id, @RequestBody PaymentRequest request) {
        return Result.success(orderService.processPayment(id, request));
    }

    @PutMapping("/{id}/cancel")
    public Result<OrderDTO> cancel(@PathVariable Long id) {
        return Result.success(orderService.cancelOrder(id));
    }
}
