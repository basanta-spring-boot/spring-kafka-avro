package com.javatechie.producer;

import com.javatechie.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SpringAvroProducer {

    @Value("${topic.name}")
    String topicName;

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public void send(Student student) {
        CompletableFuture<SendResult<String, Student>> future = kafkaTemplate.send(topicName, UUID.randomUUID().toString(), student);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + student +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        student + "] due to : " + ex.getMessage());
            }
        });
    }

}

