package com.xfef0.rabbitMQ.publisher;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.string-routing.key}")
    private String stringRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, stringRoutingKey, message);
        LOGGER.info("Message sent: {}", message);
    }

}
