package com.example.springdemo.service;

import com.example.springdemo.model.Message;

public interface DispatchServiceInterface {
    void sendMessage(String queue, Message message);
}
