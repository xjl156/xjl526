package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "waste_records")
public class WasteRecord extends BaseEntity {

    private Long storeId;
    private Long ingredientId;
    private String ingredientName;
    private BigDecimal quantity;
    private String unit;
    private String reason;
    private String remark;
}
