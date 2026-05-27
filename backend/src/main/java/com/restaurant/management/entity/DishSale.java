package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_sales")
public class DishSale extends BaseEntity {

    private LocalDate businessDate;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private Integer servedCount;
}
