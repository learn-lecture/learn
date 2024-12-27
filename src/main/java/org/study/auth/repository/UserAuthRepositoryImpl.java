package org.study.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.auth.application.interfaces.UserAuthRepository;
import org.study.auth.domain.UserAuth;
import org.study.auth.repository.entity.UserAuthEntity;
import org.study.auth.repository.jpa.JpaUserAuthRepository;
import org.study.user.application.interfaces.UserRepository;
import org.study.user.domain.User;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        return jpaUserAuthRepository.save(userAuthEntity).toUserAuth();
    }

}
