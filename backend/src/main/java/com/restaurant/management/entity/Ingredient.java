package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {

    private Long storeId;
    private Long categoryId;
    private String ingredientCode;
    private String ingredientName;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal safetyStock;
    private BigDecimal maxStock;
    private Integer shelfLifeDays;
    private Integer warningDays;
    private Integer status;
}
