package com.xfef0.rabbitMQ.publisher;

import com.xfef0.rabbitMQ.dto.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JsonPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPublisher.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json-routing.key}")
    private String jsonRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user) {
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
        LOGGER.info("JSON message sent: {}", user);
    }
}
