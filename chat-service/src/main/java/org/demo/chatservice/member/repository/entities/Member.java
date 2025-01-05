package org.demo.chatservice.member.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.demo.chatservice.member.repository.enums.Gender;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private LocalDate birthday;
    private String role;

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않음");
        }
        this.password = passwordEncoder.encode(password);
    }
}
