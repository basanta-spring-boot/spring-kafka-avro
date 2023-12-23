package com.javatechie.controller;

import com.javatechie.dto.Student;
import com.javatechie.producer.SpringAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaAvroController {

    @Autowired
    private SpringAvroProducer producer;

    @PostMapping("/events")
    public String sendKafkaEvents(@RequestBody Student student) {
        try {
            producer.send(student);
            return "Message published";
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred : " + e.getMessage());
        }

    }
}
