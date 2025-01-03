package org.demo.chatservice.oauth.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.demo.chatservice.oauth.domain.CustomOauth2User;
import org.demo.chatservice.oauth.repository.JpaMemberRepository;
import org.demo.chatservice.oauth.repository.entities.Member;
import org.demo.chatservice.oauth.repository.enums.Gender;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");
        Member member = jpaMemberRepository.findByEmail(email)
                .orElseGet(() -> registerMember(kakaoAccount));

        return new CustomOauth2User(member, oAuth2User.getAttributes());
    }

    private Member registerMember(Map<String, Object> kakaoAccount) {
        Member member = Member.builder()
                .email((String) kakaoAccount.get("email"))
                .nickname((String) ((Map) kakaoAccount.get("profile")).get("nickname"))
                .name((String) kakaoAccount.get("name"))
                .phoneNumber((String) kakaoAccount.get("phone_number"))
                .gender(Gender.valueOf(((String) kakaoAccount.get("gender")).toUpperCase()))
                .birthday(getBirthDay(kakaoAccount))
                .role("USER_ROLE")
                .build();
        return jpaMemberRepository.save(member);
    }

    private LocalDate getBirthDay(Map<String, Object> kakaoAccount) {
        String birthyear = (String) kakaoAccount.get("birthyear");
        String birthday = (String) kakaoAccount.get("birthday");
        return LocalDate.parse(birthyear + birthday, DateTimeFormatter.BASIC_ISO_DATE);
    }

}
