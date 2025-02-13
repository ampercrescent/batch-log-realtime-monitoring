package com.ampercrescent.blrm.consumer;

import com.ampercrescent.blrm.service.LogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {
    private final LogService logService;

    public LogConsumer(LogService logService) {
        this.logService = logService;
    }

    @KafkaListener(
            topics = "#{appProperties.kafka.topic}",
            groupId = "#{appProperties.kafka.consumer.groupId}",
            containerFactory = "kafkaListenerContainerBatchFactory"
    )
    public void consumeLog(String message) {
        logService.saveLog(message).subscribe(System.out::println);
    }
}