package com.microservices.payments_email.DTOs;

import java.util.UUID;

public record EmailRecordDTO(
        UUID userid,
        String emailTo,
        String subject,
        String text
) {
}
