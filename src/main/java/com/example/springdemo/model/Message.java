package com.example.springdemo.model;

import com.example.springdemo.request.MessageRequest;

import java.io.Serializable;

public class Message implements Serializable {
    private String mqName;
    private String messageBody;
    private Long messageId;

    public String getMqName() {
        return mqName;
    }

    public void setMqName(String mqName) {
        this.mqName = mqName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
