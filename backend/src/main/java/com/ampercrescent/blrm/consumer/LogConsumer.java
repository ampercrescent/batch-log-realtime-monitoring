package com.ampercrescent.blrm.consumer;

import com.ampercrescent.blrm.service.LogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class LogConsumer {
    private final LogService logService;
    private final Sinks.Many<String> sink;

    public LogConsumer(LogService logService) {
        this.logService = logService;
        this.sink = Sinks.many().multicast().directAllOrNothing();
    }

    @KafkaListener(
            topics = "#{appProperties.kafka.topic}",
            groupId = "#{appProperties.kafka.consumer.groupId}",
            containerFactory = "kafkaListenerContainerBatchFactory"
    )
    public void consumeLog(String message) {
        logService.saveLog(message).subscribe(System.out::println);
        sink.tryEmitNext(message);
    }

    public Flux<String> streamLogs() {
        return sink.asFlux().doOnCancel(() -> System.out.println("SSE 연결 종료"));
    }
}