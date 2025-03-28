package org.demo.chatservice.oauth.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.demo.chatservice.member.domain.Role;
import org.demo.chatservice.member.repository.entities.Member;
import org.demo.chatservice.member.repository.enums.Gender;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class MemberFactory {

    public static Member create(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        return switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "kakao" -> createKakaoMember(oAuth2User);
            case "google" -> createGoogleMember(oAuth2User);
            default -> throw new IllegalArgumentException("연동되지 않은 서비스입니다.");
        };
    }

    private static Member createGoogleMember(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return Member.builder()
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("given_name"))
                .name((String) attributes.get("name"))
                .role(Role.USER.getCode())
                .build();
    }

    private static Member createKakaoMember(OAuth2User oAuth2User) {
        Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
        return Member.builder()
                .email((String) kakaoAccount.get("email"))
                .nickname((String) ((Map) kakaoAccount.get("profile")).get("nickname"))
                .name((String) kakaoAccount.get("name"))
                .phoneNumber((String) kakaoAccount.get("phone_number"))
                .gender(Gender.valueOf(((String) kakaoAccount.get("gender")).toUpperCase()))
                .birthday(getBirthDay(kakaoAccount))
                .role(Role.USER.getCode())
                .build();
    }

    private static LocalDate getBirthDay(Map<String, Object> kakaoAccount) {
        String birthyear = (String) kakaoAccount.get("birthyear");
        String birthday = (String) kakaoAccount.get("birthday");
        return LocalDate.parse(birthyear + birthday, DateTimeFormatter.BASIC_ISO_DATE);
    }

}
