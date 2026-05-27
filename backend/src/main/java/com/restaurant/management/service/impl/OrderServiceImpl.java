package com.restaurant.management.service.impl;

import com.restaurant.management.dto.*;
import com.restaurant.management.entity.*;
import com.restaurant.management.repository.*;
import com.restaurant.management.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DishOrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final SalesRecordRepository salesRecordRepository;

    private static final AtomicLong ORDER_COUNTER = new AtomicLong(1);

    @Override
    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request) {
        String orderNo = "DD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + String.format("%04d", ORDER_COUNTER.getAndIncrement());

        BigDecimal total = BigDecimal.ZERO;
        for (var item : request.getItems()) {
            total = total.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        DishOrder order = new DishOrder();
        order.setOrderNo(orderNo);
        order.setStoreId(request.getStoreId());
        order.setTableNo(request.getTableNo());
        order.setGuestCount(request.getGuestCount());
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setRemark(request.getRemark());
        order = orderRepository.save(order);

        for (var item : request.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(order.getId());
            oi.setDishId(item.getDishId());
            oi.setDishName(item.getDishName());
            oi.setQuantity(item.getQuantity());
            oi.setUnitPrice(item.getUnitPrice());
            oi.setSubtotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            orderItemRepository.save(oi);
        }

        log.info("新订单创建: {} 桌号={} 金额={}", orderNo, request.getTableNo(), total);
        return getOrder(order.getId());
    }

    @Override
    public OrderDTO getOrder(Long id) {
        DishOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
        return toDTO(order);
    }

    @Override
    public OrderDTO getOrderByNo(String orderNo) {
        DishOrder order = orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + orderNo));
        return toDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersByStore(Long storeId) {
        return orderRepository.findByStoreIdOrderByCreatedAtDesc(storeId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO processPayment(Long id, PaymentRequest request) {
        DishOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));

        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("订单状态不正确，无法支付");
        }

        order.setStatus("PAID");
        order.setPaymentMethod(request.getPaymentMethod());
        order.setPaidAt(LocalDateTime.now());
        orderRepository.save(order);

        updateSalesRecord(order);

        log.info("订单支付完成: {} 方式={} 金额={}",
                order.getOrderNo(), request.getPaymentMethod(), order.getTotalAmount());
        return toDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(Long id) {
        DishOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
        log.info("订单已取消: {}", order.getOrderNo());
        return toDTO(order);
    }

    private void updateSalesRecord(DishOrder order) {
        LocalDate today = LocalDate.now();
        var existing = salesRecordRepository.findByStoreIdAndBusinessDate(order.getStoreId(), today);

        if (existing.isPresent()) {
            SalesRecord record = existing.get();
            record.setTotalCustomers(record.getTotalCustomers() + (order.getGuestCount() != null ? order.getGuestCount() : 1));
            record.setTotalAmount(record.getTotalAmount().add(order.getTotalAmount()));
            record.setBuffetAmount(record.getBuffetAmount().add(order.getTotalAmount()));
            salesRecordRepository.save(record);
        } else {
            SalesRecord record = new SalesRecord();
            record.setStoreId(order.getStoreId());
            record.setBusinessDate(today);
            record.setTotalCustomers(order.getGuestCount() != null ? order.getGuestCount() : 0);
            record.setTotalAmount(order.getTotalAmount());
            record.setBuffetAmount(order.getTotalAmount());
            record.setSingleAmount(BigDecimal.ZERO);
            salesRecordRepository.save(record);
        }
    }

    private OrderDTO toDTO(DishOrder order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNo(order.getOrderNo());
        dto.setStoreId(order.getStoreId());
        dto.setTableNo(order.getTableNo());
        dto.setGuestCount(order.getGuestCount());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt() != null ? order.getCreatedAt().toString() : null);
        dto.setPaidAt(order.getPaidAt() != null ? order.getPaidAt().toString() : null);
        dto.setRemark(order.getRemark());
        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
        dto.setItems(items.stream().map(this::toItemDTO).collect(Collectors.toList()));
        return dto;
    }

    private OrderItemDTO toItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrderId());
        dto.setDishId(item.getDishId());
        dto.setDishName(item.getDishName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
}
