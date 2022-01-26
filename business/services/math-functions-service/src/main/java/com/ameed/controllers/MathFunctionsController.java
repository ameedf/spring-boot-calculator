package com.ameed.controllers;

import com.ameed.daos.MathFunctionExecutionRepository;
import com.ameed.daos.MathFunctionRepository;
import com.ameed.entities.MathFunction;
import com.ameed.entities.MathFunctionExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.ameed.config.RabbitMQConfigurations.*;

@RestController
@RequestMapping("/math-functions")
@RequiredArgsConstructor
public class MathFunctionsController {
    private final MathFunctionRepository functionRepository;
    private final MathFunctionExecutionRepository executionRepository;
    private final AmqpTemplate amqpTemplate;
    private final RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return "Hello from " + MathFunctionsController.class.getSimpleName();
    }

    @PostMapping
    public MathFunction createMathFunction(@RequestBody MathFunction newFunction) {
        return functionRepository.save(newFunction);
    }

    @GetMapping("/{funcId}/calculate/{operands}")
    public Long createMathFunctionExecution(@PathVariable Long funcId,
                                            @PathVariable String operands) {
        return createExecution(funcId, operands, false);
    }

    @GetMapping("/{funcId}/calculate-async/{operands}")
    public Long createAsyncMathFunctionExecution(@PathVariable Long funcId,
                                                 @PathVariable String operands) {
        return createExecution(funcId, operands, true);
    }

    private Long createExecution(Long funcId, String operands, boolean async) {
        Optional<MathFunctionExecution> mathFunctionExecutionRecord = executionRepository.findByFunctionIdAndOperands(funcId, operands);
        if (mathFunctionExecutionRecord.isPresent()) {
            return mathFunctionExecutionRecord.get().getId();
        }
        Optional<MathFunction> mathFunction = functionRepository.findById(funcId);
        MathFunctionExecution mathFunctionExecution = new MathFunctionExecution();
        mathFunctionExecution.setAsync(async);
        mathFunctionExecution.setFunction(mathFunction.get());
        mathFunctionExecution.setOperands(operands);
        Long executionId = executionRepository.save(mathFunctionExecution).getId();
        if (async) {
            amqpTemplate.convertAndSend(CALC_EXCHANGE_NAME, ROUTING_KEY, executionId);
        } else {
            restTemplate.getForObject("http://calculator-service/execute/" + executionId, Long.class);
        }
        return executionId;
    }
}
