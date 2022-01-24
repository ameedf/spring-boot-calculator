package com.ameed;

import com.ameed.rabbit.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping("/execute/{execId}")
    public Long execute(@PathVariable Long execId) {
        return calculatorService.calculate(execId);
    }
}
