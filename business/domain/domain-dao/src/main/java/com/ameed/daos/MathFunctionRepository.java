package com.ameed.daos;

import com.ameed.entities.MathFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MathFunctionRepository extends JpaRepository<MathFunction, Long> {
}
