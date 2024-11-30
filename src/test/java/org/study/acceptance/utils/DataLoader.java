package org.study.acceptance.utils;

import static org.study.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.study.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.application.dto.FollowUserRequestDto;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

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

}
