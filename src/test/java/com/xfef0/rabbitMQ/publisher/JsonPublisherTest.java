package com.xfef0.rabbitMQ.publisher;

import com.xfef0.rabbitMQ.dto.User;
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
class JsonPublisherTest {

    public static final String EXCHANGE = "test_exchange";
    public static final String ROUTING_KEY = "test_json_routing_key";
    @Mock
    private RabbitTemplate rabbitTemplate;
    private JsonPublisher jsonPublisher;

    @BeforeEach
    void setUp() {
        jsonPublisher = new JsonPublisher(rabbitTemplate);
    }

    @Test
    void shouldPublishJsonMessage() {
        ReflectionTestUtils.setField(jsonPublisher, "exchange", EXCHANGE);
        ReflectionTestUtils.setField(jsonPublisher, "jsonRoutingKey", ROUTING_KEY);
        User user = new User();
        user.setId(1);
        user.setFirstName("Logan");
        user.setLastName("Paul");

        jsonPublisher.sendJsonMessage(user);

        verify(rabbitTemplate, times(1))
                .convertAndSend(eq(EXCHANGE), eq(ROUTING_KEY), eq(user));
    }
}