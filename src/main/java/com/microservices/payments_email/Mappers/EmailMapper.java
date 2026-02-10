package com.microservices.payments_email.Mappers;


import com.microservices.payments_email.DTOs.EmailRecordDTO;
import com.microservices.payments_email.Model.EmailModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailRecordDTO ToDTO(EmailModel model);

    EmailModel ToEntity(EmailRecordDTO dto);
}
