package org.demo.chatservice.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.member.application.dto.MemberDto;
import org.demo.chatservice.member.domain.CustomUserDetails;
import org.demo.chatservice.member.domain.Role;
import org.demo.chatservice.member.repository.JpaMemberRepository;
import org.demo.chatservice.member.repository.entities.Member;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final JpaMemberRepository jpaMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = jpaMemberRepository.findByName(username).orElseThrow(IllegalArgumentException::new);
        if (Role.fromCode(member.getRole()) != Role.CONSULTANT) {
            throw new AccessDeniedException("상담사가 아닙니다.");
        }
        return new CustomUserDetails(member);
    }

    @Transactional
    public MemberDto saveMember(MemberDto dto) {
        Member member = dto.to();
        member.updatePassword(dto.password(), dto.confirmedPassword(), passwordEncoder);
        return MemberDto.from(jpaMemberRepository.save(member));
    }

}
