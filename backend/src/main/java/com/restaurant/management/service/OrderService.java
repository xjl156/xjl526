package com.restaurant.management.service;

import com.restaurant.management.dto.*;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(CreateOrderRequest request);
    OrderDTO getOrder(Long id);
    OrderDTO getOrderByNo(String orderNo);
    List<OrderDTO> getOrdersByStore(Long storeId);
    OrderDTO processPayment(Long id, PaymentRequest request);
    OrderDTO cancelOrder(Long id);
}
