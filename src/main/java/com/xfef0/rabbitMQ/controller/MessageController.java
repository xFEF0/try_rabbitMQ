package com.xfef0.rabbitMQ.controller;

import com.xfef0.rabbitMQ.publisher.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageProducer messageProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent");
    }
}
