package org.study.auth.application.interfaces;

import org.study.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String randomToken);

}
