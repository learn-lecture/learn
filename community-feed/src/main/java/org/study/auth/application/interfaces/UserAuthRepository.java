package org.study.auth.application.interfaces;

import org.study.auth.domain.UserAuth;
import org.study.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth userAuth, User user);
    UserAuth loginUser(String email, String password, String fcmToken);

}
