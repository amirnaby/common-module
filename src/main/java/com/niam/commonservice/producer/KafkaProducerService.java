package com.niam.commonservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!logger-off")
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional("kafkaTransactionManager")
    public void produce(String topicName, Object logModel) {
        try {
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(logModel));
        } catch (JsonProcessingException | KafkaException ignored) {
            log.error("couldn't save log! {}", logModel);
        }
    }
}