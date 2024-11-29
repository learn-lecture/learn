package org.study.auth.repository;

import com.mysema.commons.lang.Assert;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.auth.application.interfaces.EmailVerificationRepository;
import org.study.auth.domain.Email;
import org.study.auth.repository.entity.EmailVerificationEntity;
import org.study.auth.repository.jpa.JpaEmailVerificationRepository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void createEmailVerification(Email email, String token) {
        if (checkAlreadyVerification(token, email.getEmailText())) {
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(email.getEmailText(), token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    private boolean checkAlreadyVerification(String token, String emailAddress) {
        return jpaEmailVerificationRepository.findByEmail(emailAddress)
                .map(entity -> {
                    Assert.isFalse(entity.isVerified(), "이미 인증된 이메일입니다.");
                    entity.updateToken(token);
                    return true;
                })
                .orElse(false);
    }

}
