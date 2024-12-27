package org.study.auth.repository;

import com.mysema.commons.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.auth.application.interfaces.EmailVerificationRepository;
import org.study.auth.domain.Email;
import org.study.auth.repository.entity.EmailVerificationEntity;
import org.study.auth.repository.jpa.JpaEmailVerificationRepository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        if (checkAlreadyVerification(token, email.getEmailText())) {
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(email.getEmailText(), token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        EmailVerificationEntity emailVerificationEntity = getEmailVerificationEntityWithThrow(email.getEmailText());
        Assert.isTrue(emailVerificationEntity.hasSameToken(token), "토큰 값이 유효하지 않습니다.");
        Assert.isFalse(emailVerificationEntity.isVerified(), "이미 인증된 이메일입니다.");

        emailVerificationEntity.verity();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        EmailVerificationEntity emailVerificationEntity = getEmailVerificationEntityWithThrow(email.getEmailText());
        return emailVerificationEntity.isVerified();
    }

    private EmailVerificationEntity getEmailVerificationEntityWithThrow(String emailAddress) {
        return jpaEmailVerificationRepository.findByEmail(emailAddress)
                .orElseThrow(() -> new IllegalArgumentException("인증 요청하지 않은 이메일입니다."));
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
