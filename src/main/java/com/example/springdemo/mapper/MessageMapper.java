package com.example.springdemo.mapper;

import com.example.springdemo.DAO.Message;
import com.example.springdemo.request.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message messageToDao(com.example.springdemo.model.Message message) {
        var messageDao = new Message();
        messageDao.setMessageId(message.getMessageId());
        messageDao.setMqName(message.getMqName());
        messageDao.setMessageBody(message.getMessageBody());
        return messageDao;
    }

    public com.example.springdemo.model.Message daoToMessage(Message messageDao) {
        var message = new com.example.springdemo.model.Message();
        message.setMessageId(messageDao.getMessageId());
        message.setMqName(messageDao.getMqName());
        message.setMessageBody(messageDao.getMessageBody());
        return message;
    }

    public com.example.springdemo.model.Message requestToMessage(MessageRequest messageRequest) {
        var message = new com.example.springdemo.model.Message();
        message.setMessageId(messageRequest.getMessageId());
        message.setMqName(messageRequest.getMqName());
        message.setMessageBody(messageRequest.getMessageBody());
        return message;
    }
}
