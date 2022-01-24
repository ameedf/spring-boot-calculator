package com.ameed.rabbit;

import com.ameed.config.RabbitMQConfigurations;
import com.ameed.daos.MathFunctionExecutionRepository;
import com.ameed.entities.MathFunctionExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CalculatorService {
    private final MathFunctionExecutionRepository mathFunctionExecutionRepository;

    @RabbitListener(queues = RabbitMQConfigurations.CALC_QUEUE_NAME)
    public void calculateAsync(Long executionId) {
        calculate(executionId);
    }

    public Long calculate(Long executionId) {
        MathFunctionExecution record = mathFunctionExecutionRepository.findById(executionId)
                .orElseThrow();
        record.setResult(new Random().nextDouble() * 10000);
        long executionTimeInMs = Math.abs(new Random().nextInt(5000) + 1000);
        try {
            System.out.println("Sleeping to " + executionTimeInMs + " ms");
            TimeUnit.MILLISECONDS.sleep(executionTimeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        record.setExecutionTimeInMs(executionTimeInMs);
        return executionId;
    }
}
