package com.javatechie.consumer;

import com.javatechie.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringAvroConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void read(ConsumerRecord<String, Student> record) {
        String key = record.key();
        Student student = record.value();
        log.info("Avro message received for key : " + key + " value : " + student.toString());
    }

}
