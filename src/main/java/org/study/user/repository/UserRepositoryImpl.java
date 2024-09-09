package org.study.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.user.application.interfaces.UserRepository;
import org.study.user.domain.User;
import org.study.user.repository.entity.UserEntity;
import org.study.user.repository.jpa.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return jpaUserRepository.save(userEntity)
            .toUser();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toUser();
    }
}
