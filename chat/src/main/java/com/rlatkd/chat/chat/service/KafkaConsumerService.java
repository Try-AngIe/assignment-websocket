package com.rlatkd.chat.chat.service;

import com.rlatkd.chat.chat.entity.ChatMessage;
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

    /**
     * - KafkaListener 어노테이션을 통해 Kafka로부터 메시지를 받을 수 있음
     * - template.convertAndSend를 통해 WebSocket으로 메시지를 전송
     * - Message를 작성할 때 경로 잘 보고 import
     */
    @KafkaListener(
            topics = "${topic.name}",
            containerFactory = "chatKafkaListenerFactory"
    )
    public void listen(ChatMessage chatMessage) {
        log.info("[KAFKA] listened: " + chatMessage);
        template.convertAndSend("/topic/group" + chatMessage);
    }
}
