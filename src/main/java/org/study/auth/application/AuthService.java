package org.study.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.auth.application.dto.CreateUserAuthRequestDto;
import org.study.auth.application.interfaces.EmailVerificationRepository;
import org.study.auth.application.interfaces.UserAuthRepository;
import org.study.auth.domain.Email;
import org.study.auth.domain.UserAuth;
import org.study.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        validateEmailVerification(dto);

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    private void validateEmailVerification(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }
    }

}
