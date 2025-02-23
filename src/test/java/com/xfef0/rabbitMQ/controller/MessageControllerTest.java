package com.xfef0.rabbitMQ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfef0.rabbitMQ.dto.User;
import com.xfef0.rabbitMQ.publisher.JsonPublisher;
import com.xfef0.rabbitMQ.publisher.MessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    private static final String API_PREFIX = "/api/v1";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @MockitoBean
    private MessageProducer messageProducer;
    @MockitoBean
    private JsonPublisher jsonPublisher;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSendMessage() throws Exception {
        String message = "testing publish endpoint";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(API_PREFIX + "/publish")
                .param("message", message));

        verify(messageProducer, times(1)).sendMessage(eq(message));
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("Message sent"));
    }

    @Test
    void shouldSendJsonMessage() throws Exception {
        User user = new User();
        user.setId(1);
        user.setLastName("Last");
        user.setFirstName("First");
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(API_PREFIX + "/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(user))
        );

        verify(jsonPublisher, times(1)).sendJsonMessage(eq(user));
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("JSON message sent"));
    }

}