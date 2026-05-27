package com.restaurant.management.repository;

import com.restaurant.management.entity.ProfitTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface ProfitTargetRepository extends JpaRepository<ProfitTarget, Long> {
}
