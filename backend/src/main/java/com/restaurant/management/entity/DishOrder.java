package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_orders")
public class DishOrder extends BaseEntity {

    private String orderNo;
    private Long storeId;
    private String tableNo;
    private Integer guestCount;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String status;
    private String remark;
    private LocalDateTime paidAt;
}
