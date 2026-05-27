package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    private Long storeId;
    private Long categoryId;
    private String dishCode;
    private String dishName;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private Integer isBuffet;
    private Integer status;
    private Integer priorityScore;
}
