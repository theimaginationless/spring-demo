package com.example.springdemo.controller;

import com.example.springdemo.dao.MessageDAO;
import com.example.springdemo.mapper.MessageMapper;
import com.example.springdemo.model.Message;
import com.example.springdemo.repository.MessageRepository;
import com.example.springdemo.request.MessageRequest;
import com.example.springdemo.service.DispatchServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api")
public class SpringDemoController {
    private final Logger logger = LoggerFactory.getLogger(SpringDemoController.class);
    private final DispatchServiceInterface dispatchService;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public SpringDemoController(DispatchServiceInterface dispatchService,
                                MessageRepository messageRepository,
                                MessageMapper messageMapper) {
        this.dispatchService = dispatchService;
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @PostMapping(value = "/sendMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody List<MessageRequest> messageRequestList) {
        logger.debug("Request have " + messageRequestList.size() + " messages.");

        messageRequestList.stream()
                .map(messageMapper::requestToMessage)
                .forEach(message -> dispatchService.sendMessage(message.getMqName(), message));
    }

    @GetMapping(value = "/getLog")
    public ResponseEntity<List<Message>> getLog(
            @RequestParam(required = false) Long messageId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate,
            @RequestParam String mqName)
    {
        logger.debug("Request for messageId='" + messageId + ",\n" +
                     "startDate='" + startDate + "'\n" +
                     "endDate='" + endDate + "'\n" +
                     "mqName='" + mqName + "'.");

        List<MessageDAO> messagesDao;
        if (messageId != null) {
            messagesDao = new ArrayList<>();
            messagesDao.add(messageRepository.findBy(messageId, startDate, endDate, mqName));
        }
        else {
            messagesDao = messageRepository.findAllBy(startDate, endDate, mqName);
        }

        List<Message> messages = messagesDao.stream()
                .map(messageMapper::daoToMessage)
                .collect(Collectors.toList());

        logger.debug("Count of fetched messages: " + messages.size() + ".");
        return ResponseEntity.ok(messages);
    }
}
