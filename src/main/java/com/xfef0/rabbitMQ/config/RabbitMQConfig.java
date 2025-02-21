package com.xfef0.rabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.string-queue.name}")
    private String stringQueue;
    @Value("${rabbitmq.string-routing.key}")
    private String stringRoutingKey;
    @Value("${rabbitmq.json-queue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.json-routing.key}")
    private String jsonRoutingKey;

    @Bean
    public Queue stringQueue() {
        return new Queue(stringQueue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding stringBinding() {
        return BindingBuilder.bind(stringQueue())
                .to(exchange())
                .with(stringRoutingKey);
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }
}
