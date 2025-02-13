package com.ampercrescent.blrm.web;

import com.ampercrescent.blrm.consumer.LogConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/logs")
public class LogStreamController {

    private final LogConsumer logConsumer;

    public LogStreamController(LogConsumer logConsumer) {
        this.logConsumer = logConsumer;
    }

    @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> streamLogs() {
        return logConsumer.streamLogs();
    }
}
