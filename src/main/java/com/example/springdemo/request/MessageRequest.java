package com.example.springdemo.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class MessageRequest {
    @NotEmpty
    private String mqName;

    @NotEmpty
    private String messageBody;

    @Min(0)
    private Long messageId;
}
