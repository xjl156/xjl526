package com.restaurant.management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String orderNo;
    private Long storeId;
    private String tableNo;
    private Integer guestCount;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String status;
    private String remark;
    private String createdAt;
    private String paidAt;
    private List<OrderItemDTO> items;
}
