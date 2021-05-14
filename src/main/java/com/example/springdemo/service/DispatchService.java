package com.example.springdemo.service;

import com.example.springdemo.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DispatchService implements DispatchServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(DispatchService.class);
    private final JmsTemplate jmsTemplate;

    @Autowired
    public DispatchService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String queue, Message message) {
        logger.debug("Message with id'" + message.getMessageId() + "' send to queue '" + queue + "'.");
        jmsTemplate.convertAndSend(queue, message);
    }
}
