package org.study.common.principle;

import lombok.Getter;
import org.study.auth.domain.UserRole;

@Getter
public class UserPrinciple {

    private Long userId;
    private UserRole userRole;

    public UserPrinciple(Long userId, String role) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(role);
    }

}
