package org.study.auth.application.interfaces;

import org.study.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);

}
