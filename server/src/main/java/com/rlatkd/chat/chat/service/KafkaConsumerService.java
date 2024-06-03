package com.rlatkd.chat.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rlatkd.chat.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    /**
     * - KafkaListener 어노테이션을 통해 Kafka로부터 메시지를 받을 수 있음
     * - template.convertAndSend를 통해 WebSocket으로 메시지를 전송
     * - Message를 작성할 때 경로 잘 보고 import
     */

    @KafkaListener(
            topics = "${topic.name}",
            groupId = "test-group"
    )
    public void listen(String chatMessage) {
        log.info("[KAFKA] listened: " + chatMessage);
        try {
            ChatMessageDto chatMessageDto = mapper.readValue(chatMessage, ChatMessageDto.class);
            template.convertAndSend("/topic/group", chatMessageDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
}
