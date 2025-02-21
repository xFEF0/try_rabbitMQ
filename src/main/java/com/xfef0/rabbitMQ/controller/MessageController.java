package com.xfef0.rabbitMQ.controller;

import com.xfef0.rabbitMQ.dto.User;
import com.xfef0.rabbitMQ.publisher.JsonPublisher;
import com.xfef0.rabbitMQ.publisher.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageProducer messageProducer;
    private final JsonPublisher jsonMessageProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        jsonMessageProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON message sent");
    }
}
