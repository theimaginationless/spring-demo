package com.example.springdemo.service;

import com.example.springdemo.mapper.MessageMapper;
import com.example.springdemo.model.Message;
import com.example.springdemo.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReceiverService implements ReceiverServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(DispatchService.class);
    private final String THREAD_NAME = ReceiverService.class.getName();
    private final ExecutorService executorService;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public ReceiverService(@Value("${config.activeTaskNumber}") int activeTaskNumber,
                           MessageRepository messageRepository,
                           MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        var threadFactory = new ThreadFactory() {
            private final AtomicLong threadIdx = new AtomicLong(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("Thread-" + THREAD_NAME + "-" + threadIdx.getAndIncrement());
                return thread;
            }
        };

        executorService = Executors.newFixedThreadPool(activeTaskNumber, threadFactory);
    }

    @JmsListener(destination = "${mq.queue.first-queue}")
    @JmsListener(destination = "${mq.queue.second-queue}")
    public void receiveMessage(Message message) {
        logger.debug("Message with id '" + message.getMessageId() +
                "' from queue '" + message.getMqName() + "' was received!.");
        executorService.submit(() -> {
            messageRepository.save(messageMapper.messageToDao(message));
            logger.debug("Message '" + message.getMessageBody() +
                    "' from queue '" + message.getMqName() + "' was saved to database!.");
        });
    }
}
