package com.example.springdemo.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MessageRequest {
    @NotEmpty
    private String mqName;

    @NotEmpty
    private String messageBody;

    @Min(0)
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
