package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity {

    private Long storeId;
    private Long ingredientId;
    private BigDecimal quantity;
    private BigDecimal reservedQuantity;
    private LocalDate productionDate;
    private LocalDate expiryDate;
    private String batchNo;
    private String supplier;
    private BigDecimal unitPrice;
}
