package org.demo.chatservice.member.domain;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    USER("ROLE_USER"),
    CONSULTANT("ROLE_CONSULTANT"),
    ;

    private final String code;

    public static Role fromCode(String code) {
        return Arrays.stream(values())
                .filter(role -> role.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

}
