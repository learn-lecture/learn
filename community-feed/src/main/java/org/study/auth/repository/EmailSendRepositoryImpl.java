package org.study.auth.repository;

import org.springframework.stereotype.Repository;
import org.study.auth.application.interfaces.EmailSendRepository;
import org.study.auth.domain.Email;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // todo
    }

}
