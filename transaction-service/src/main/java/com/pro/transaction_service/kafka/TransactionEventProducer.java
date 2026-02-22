package com.pro.transaction_service.kafka;

import com.pro.transaction_service.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionEventProducer {

    private final KafkaTemplate<String,TransactionEvent> kafkaTemplate;

    public void publishTransactionEvent(TransactionEvent event){
        kafkaTemplate.send("transaction_event",event);
    }
}
