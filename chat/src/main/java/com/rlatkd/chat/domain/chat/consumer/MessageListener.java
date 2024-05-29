package com.rlatkd.chat.domain.chat.consumer;

import com.rlatkd.chat.domain.chat.constants.KafkaConstants;
import com.rlatkd.chat.domain.chat.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    /**
     - KafkaListener 어노테이션을 통해 Kafka로부터 메시지를 받을 수 있음
     - template.convertAndSend를 통해 WebSocket으로 메시지를 전송
     - Message를 작성할 때 경로 잘 보고 import
     */

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.KAFKA_GROUP_ID
    )

    public void listen(Message message) {
        log.info("[----------Kafka Listen----------]");
        template.convertAndSend("/topic/group", message);
    }

}
