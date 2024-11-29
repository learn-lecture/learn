package org.study.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.auth.application.dto.SendEmailRequestDto;
import org.study.auth.application.interfaces.EmailSendRepository;
import org.study.auth.application.interfaces.EmailVerificationRepository;
import org.study.auth.domain.Email;
import org.study.auth.domain.RandomTokenGenerator;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

}
