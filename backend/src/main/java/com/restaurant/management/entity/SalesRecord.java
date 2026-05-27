package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sales_records")
public class SalesRecord extends BaseEntity {

    private Long storeId;
    private LocalDate businessDate;
    private Integer totalCustomers;
    private BigDecimal totalAmount;
    private BigDecimal buffetAmount;
    private BigDecimal singleAmount;
}
