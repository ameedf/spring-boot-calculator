package com.ameed.daos;

import com.ameed.entities.MathFunctionExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MathFunctionExecutionRepository extends JpaRepository<MathFunctionExecution, Long> {
    Optional<MathFunctionExecution> findByFunctionIdAndOperands(Long functionId, String operands);
}
