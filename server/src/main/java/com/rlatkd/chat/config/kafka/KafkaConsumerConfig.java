package com.rlatkd.chat.config.kafka;

import com.rlatkd.chat.chat.dto.ChatMessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * - listener(consumer)는 Kafka로부터 메시지를 받는 곳
 * - GROUP_ID_CONFIG는 consumer group id를 설정
 * - KEY_DESERIALIZER_CLASS_CONFIG와 VALUE_DESERIALIZER_CLASS_CONFIG는 Kafka에서 받은 데이터의 키와 값을 역직렬화함
 * - AUTO_OFFSET_RESET_CONFIG에는 latest(가장 최근에 생성된 메시지를 offset reset), earliest(가장 오래된 메시지를), none의 값을 입력할 수 있음
 **/
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> chatKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(chatConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> chatConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return configurations;
    }
}
