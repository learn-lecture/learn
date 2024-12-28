package org.study.acceptance.utils;

import static org.study.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.study.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.study.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.study.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.study.auth.application.dto.CreateUserAuthRequestDto;
import org.study.auth.application.dto.SendEmailRequestDto;
import org.study.user.application.dto.FollowUserRequestDto;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        for (int i = 1; i <= 3; i++) {
            createUser("user" + i + "@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery(
                        "SELECT token FROM community_email_verification WHERE email = ?",
                        String.class
                )
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery(
                        "SELECT isVerified FROM EmailVerificationEntity WHERE email = :email"
                        , Boolean.class
                ).setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email) {
        return (Long) entityManager.createNativeQuery(
                        "SELECT user_id FROM community_user_auth WHERE email = ?",
                        Long.class
                )
                .setParameter(1, email)
                .getSingleResult();
    }

    public void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "name", ""));
    }

}
