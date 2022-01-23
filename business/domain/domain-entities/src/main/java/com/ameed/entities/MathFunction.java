package com.ameed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MATH_FUNCTIONS", schema = "calculator")
public class MathFunction extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private FunctionName name;

    private String formula;

}
