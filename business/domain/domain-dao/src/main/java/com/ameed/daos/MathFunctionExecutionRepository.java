package com.ameed.daos;

import com.ameed.entities.MathFunctionExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MathFunctionExecutionRepository extends JpaRepository<MathFunctionExecution, Long> {
}
