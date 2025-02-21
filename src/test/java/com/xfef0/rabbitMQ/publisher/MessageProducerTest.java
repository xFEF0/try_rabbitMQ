package com.xfef0.rabbitMQ.publisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageProducerTest {

    private static final String ROUTING_KEY = "test_routing_key";
    private static final String EXCHANGE = "test_exchange";

    @Mock
    private RabbitTemplate rabbitTemplate;

    private MessageProducer messageProducer;

    @BeforeEach
    void setUp() {
        messageProducer = new MessageProducer(rabbitTemplate);
    }

    @Test
    void shouldSendMessage() {
        ReflectionTestUtils.setField(messageProducer, "exchange", EXCHANGE);
        ReflectionTestUtils.setField(messageProducer, "stringRoutingKey", ROUTING_KEY);
        String message = "testing message production";

        messageProducer.sendMessage(message);

        verify(rabbitTemplate, times(1))
                .convertAndSend(eq(EXCHANGE), eq(ROUTING_KEY), eq(message));
    }
}