package com.microservices.payments_email.Services;

import com.microservices.payments_email.DTOs.EmailRecordDTO;
import com.microservices.payments_email.Enums.StatusEmail;
import com.microservices.payments_email.Mappers.EmailMapper;
import com.microservices.payments_email.Model.EmailModel;
import com.microservices.payments_email.Repositories.EmailRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailMapper mapper;
    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailRecordDTO dto) {
        EmailModel emailModel1 = mapper.ToEntity(dto);

        try {
            emailModel1.setSendDateEmail(LocalDateTime.now());
            emailModel1.setEmailFrom(emailFrom);

            emailSender.send(createMessage(emailModel1));

            emailModel1.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel1.setStatusEmail(StatusEmail.ERROR);
        }


        return emailRepository.save(emailModel1);
    }

    private SimpleMailMessage createMessage(EmailModel model) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(model.getEmailTo());
        message.setSubject(model.getSubject());
        message.setText(model.getText());
        return message;
    }
}