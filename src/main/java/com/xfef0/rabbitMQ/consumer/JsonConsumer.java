package com.xfef0.rabbitMQ.consumer;

import com.xfef0.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json-queue.name}"})
    public void consume(User user) {
        LOGGER.info("JSON message received: {}", user);
    }

}
