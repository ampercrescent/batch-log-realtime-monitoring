package com.ampercrescent.blrm.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class LogConsumer {
    private final KafkaConsumer<String, String> kafkaConsumer;

    public LogConsumer(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
                       @Value("${spring.kafka.consumer.group-id}") String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.kafkaConsumer = new KafkaConsumer<>(props);
        this.kafkaConsumer.subscribe(Collections.singletonList("log-topic"));
    }

    @Scheduled(fixedRateString = "${scheduler.interval}")
    public void consumeLogPeriodically() {
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(5));

        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.value());
        }
    }
}