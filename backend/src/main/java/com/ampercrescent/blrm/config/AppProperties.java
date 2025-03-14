package com.ampercrescent.blrm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private KafkaProperties kafka;
    private ElasticsearchProperties elasticsearch;

    @Getter
    @Setter
    public static class KafkaProperties {
        private String bootstrapServers;
        private String topic;
        private KafkaConsumerProperties consumer;

        @Getter
        @Setter
        public static class KafkaConsumerProperties {
            private String groupId;
            private String autoOffsetReset;
        }
    }

    @Getter
    @Setter
    public static class ElasticsearchProperties{
        private String baseUrl;
    }
}
