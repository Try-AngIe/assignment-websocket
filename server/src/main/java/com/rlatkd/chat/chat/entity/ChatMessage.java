package com.rlatkd.chat.chat.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {

    private ChatMessageType type;   //메시지 타입 (입장 or 채팅메시지)
    private String author;          //메시지 작성자
    private String content;         //메시지 내용
    private String timestamp;       //메시지 작성시간

}
