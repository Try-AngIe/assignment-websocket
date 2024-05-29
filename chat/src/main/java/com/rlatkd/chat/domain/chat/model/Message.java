package com.rlatkd.chat.domain.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private String author; //메시지 작성자
    private String content; //메시지 내용
    private String timestamp = LocalDateTime.now().toString();; //메시지 작성시간


    public Message() {
    }

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override //toString 재정의
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
