package org.study.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.auth.application.dto.CreateUserAuthRequestDto;
import org.study.auth.application.dto.LoginRequestDto;
import org.study.auth.application.dto.UserAccessTokenResponseDto;
import org.study.auth.application.interfaces.EmailVerificationRepository;
import org.study.auth.application.interfaces.UserAuthRepository;
import org.study.auth.domain.Email;
import org.study.auth.domain.TokenProvider;
import org.study.auth.domain.UserAuth;
import org.study.user.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        validateEmailVerification(dto);

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password(), dto.fcmToken());
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());
        return new UserAccessTokenResponseDto(token);
    }

    private void validateEmailVerification(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }
    }

}
