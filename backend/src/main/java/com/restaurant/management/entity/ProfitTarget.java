package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profit_targets")
public class ProfitTarget extends BaseEntity {

    private Long storeId;
    private BigDecimal dailyTarget;
    private BigDecimal monthlyTarget;
}
