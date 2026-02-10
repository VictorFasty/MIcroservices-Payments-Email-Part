package com.microservices.payments_email.Consumer;

import com.microservices.payments_email.DTOs.EmailRecordDTO;
import com.microservices.payments_email.Services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService service;

    public EmailConsumer(EmailService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receive(@Payload EmailRecordDTO dto) {
        service.sendEmail(dto);
    }
}
