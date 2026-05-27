package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ingredient_consumption")
public class IngredientConsumption extends BaseEntity {

    private Long storeId;
    private Long ingredientId;
    private String ingredientName;
    private BigDecimal quantity;
    private LocalDate consumptionDate;
}
