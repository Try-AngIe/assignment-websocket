package com.rlatkd.chat.chat.service;

import com.rlatkd.chat.chat.dto.ChatMessageDto;
import com.rlatkd.chat.chat.entity.ChatMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, ChatMessageDto> kafkaChatTemplate;

    @Value("${topic.name}")
    private String KAFKA_TOPIC;

    public void enter(String username) {
        log.info("[KAFKA] enter: %s\n".formatted(username));
        ChatMessageDto enterMessage = new ChatMessageDto(
                ChatMessageType.ENTER,
                username,
                "ENTERED",
                LocalDateTime.now().toString()
        );
        kafkaChatTemplate.send(KAFKA_TOPIC, enterMessage);
    }

    public void sendChatMessage(ChatMessageDto message) {
        log.info("[KAFKA] produce message: %s\n".formatted(message.toString()));
        kafkaChatTemplate.send(KAFKA_TOPIC, message);
    }
}
