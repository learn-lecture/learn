package org.demo.chatservice.member.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.demo.chatservice.member.repository.entities.Member;
import org.demo.chatservice.oauth.domain.CustomOauth2User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends CustomOauth2User implements UserDetails {

    public CustomUserDetails(Member member, Map<String, Object> attributes) {
        super(member, attributes);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(member.getRole()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

}
