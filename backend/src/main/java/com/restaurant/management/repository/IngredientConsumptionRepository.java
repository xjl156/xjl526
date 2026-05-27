package com.restaurant.management.repository;

import com.restaurant.management.entity.IngredientConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface IngredientConsumptionRepository extends JpaRepository<IngredientConsumption, Long> {

    List<IngredientConsumption> findByStoreIdAndConsumptionDateBetween(Long storeId, LocalDate start, LocalDate end);
}
