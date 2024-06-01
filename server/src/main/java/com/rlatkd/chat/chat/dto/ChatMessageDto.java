package com.rlatkd.chat.chat.dto;

import com.rlatkd.chat.chat.entity.ChatMessage;
import com.rlatkd.chat.chat.entity.ChatMessageType;

import java.io.Serializable;

public record ChatMessageDto(
        ChatMessageType type,
        String author,
        String content,
        String timestamp
) implements Serializable {

    public static ChatMessage toModel(ChatMessageDto messageDto) {
        return ChatMessage.builder()
                .type(messageDto.type)
                .author(messageDto.author)
                .content(messageDto.content)
                .timestamp(messageDto.timestamp)
                .build();
    }

    public static ChatMessageDto fromModel(ChatMessage message) {
        return new ChatMessageDto(
                message.getType(),
                message.getAuthor(),
                message.getContent(),
                message.getTimestamp()
        );
    }
}
