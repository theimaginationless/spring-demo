package com.example.springdemo.mapper;

import com.example.springdemo.dao.MessageDAO;
import com.example.springdemo.request.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageDAO messageToDao(com.example.springdemo.model.Message message) {
        var messageDao = new MessageDAO();
        messageDao.setMessageId(message.getMessageId());
        messageDao.setMqName(message.getMqName());
        messageDao.setMessageBody(message.getMessageBody());
        return messageDao;
    }

    public com.example.springdemo.model.Message daoToMessage(MessageDAO messageDao) {
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
