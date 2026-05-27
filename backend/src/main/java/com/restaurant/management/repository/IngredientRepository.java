package com.restaurant.management.repository;

import com.restaurant.management.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
