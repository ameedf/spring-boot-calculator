package com.ameed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FUNC_EXECUTIONS")
public class MathFunctionExecution extends AbstractEntity {

    @ManyToOne
    private MathFunction function;

    private Double result;

    private Long executionTimeInMs;

    private Boolean async;

    private String operands; // "1,20"
}
