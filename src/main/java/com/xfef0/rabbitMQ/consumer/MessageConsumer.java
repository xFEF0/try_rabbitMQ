package com.xfef0.rabbitMQ.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.string-queue.name}"})
    public void consume(String message) {
        LOGGER.info("Message received: {}", message);
    }
}
