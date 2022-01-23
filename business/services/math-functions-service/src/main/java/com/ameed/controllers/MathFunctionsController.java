package com.ameed.controllers;

import com.ameed.daos.MathFunctionExecutionRepository;
import com.ameed.daos.MathFunctionRepository;
import com.ameed.entities.MathFunction;
import com.ameed.entities.MathFunctionExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math-functions")
@RequiredArgsConstructor
public class MathFunctionsController {
    private final MathFunctionRepository functionRepository;
    private final MathFunctionExecutionRepository executionRepository;

    @PostMapping
    public MathFunction createMathFunction(@RequestBody MathFunction newFunction) {
        return functionRepository.save(newFunction);
    }

    @PostMapping("/calculate")
    public MathFunctionExecution createMathFunctionExecution(@RequestBody MathFunctionExecution mathFunctionExecution) {
        return executionRepository.save(mathFunctionExecution);
    }
}
