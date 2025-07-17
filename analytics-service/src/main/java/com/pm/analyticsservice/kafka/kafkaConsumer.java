package com.pm.analyticsservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
public class kafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(kafkaConsumer.class);

    // consume byte[] event here
    // group id to identify consumer
    @KafkaListener(topics = "patient", groupId = "analytics-group")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
           // Do analytics processing here

            log.info("Received Patient Event: [patientId={}, patientName={}, patientEmail={}]",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail());

        } catch (Exception e) {
            log.error("Failed to deserialize protobuf message event {}: " , e.getMessage());
        }
    }

}
