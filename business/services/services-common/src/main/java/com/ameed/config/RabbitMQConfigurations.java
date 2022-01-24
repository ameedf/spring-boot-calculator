package com.ameed.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigurations {

    public static final String CALC_QUEUE_NAME = "calc-queue";
    public static final String CALC_EXCHANGE_NAME = "calc-exchange";
    public static final String ROUTING_KEY = "calc-key";

    @Bean
    public Queue queue() {
        return new Queue(CALC_QUEUE_NAME);
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(CALC_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(direct())
                .with(ROUTING_KEY);
    }
}
