package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder extends BaseEntity {

    private String orderNo;
    private Long storeId;
    private BigDecimal totalAmount;
    private String status;
    private String supplier;
    private String remark;
}
