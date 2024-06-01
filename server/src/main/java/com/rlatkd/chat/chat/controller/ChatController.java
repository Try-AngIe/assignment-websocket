package com.rlatkd.chat.chat.controller;

import com.rlatkd.chat.chat.dto.ChatMessageDto;
import com.rlatkd.chat.chat.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducerService producerService;

    @MessageMapping("/message")
    public void broadcastGroupMessage(@Payload ChatMessageDto chatMessage) {
        producerService.sendChatMessage(chatMessage);
    }
}