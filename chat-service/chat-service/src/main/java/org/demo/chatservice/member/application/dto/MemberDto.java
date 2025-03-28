package org.demo.chatservice.member.application.dto;

import java.time.LocalDate;
import org.demo.chatservice.member.repository.entities.Member;
import org.demo.chatservice.member.repository.enums.Gender;

public record MemberDto(
        Long id,
        String email,
        String nickname,
        String name,
        String password,
        String confirmedPassword,
        Gender gender,
        String phoneNumber,
        LocalDate birthday,
        String role
) {

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getName(),
                null,
                null,
                member.getGender(),
                member.getPhoneNumber(),
                member.getBirthday(),
                member.getRole()
        );
    }

    public Member to() {
        return Member.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .role(role)
                .build();
    }
}
