package com.xfef0.rabbitMQ.controller;

import com.xfef0.rabbitMQ.publisher.MessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockitoBean
    private MessageProducer messageProducer;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldSendMessage() throws Exception {
        String message = "testing publish endpoint";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/publish")
                .param("message", message));

        verify(messageProducer, times(1)).sendMessage(eq(message));
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("Message sent"));
    }

}