package org.demo.chatservice.oauth.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.demo.chatservice.oauth.domain.CustomOauth2User;
import org.demo.chatservice.member.repository.JpaMemberRepository;
import org.demo.chatservice.member.repository.entities.Member;
import org.demo.chatservice.member.repository.enums.Gender;
import org.demo.chatservice.oauth.utils.MemberFactory;
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
        String email = oAuth2User.getAttribute("email");
        Member member = jpaMemberRepository.findByEmail(email)
                .orElseGet(() -> jpaMemberRepository.save(MemberFactory.create(userRequest, oAuth2User)));

        return new CustomOauth2User(member, oAuth2User.getAttributes());
    }

}
