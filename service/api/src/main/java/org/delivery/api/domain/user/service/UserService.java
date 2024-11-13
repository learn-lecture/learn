package org.delivery.api.domain.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.user.exception.UserExceptionType;
import org.delivery.common.exception.model.NotFoundException;
import org.delivery.db.user.User;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.vo.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User register(final User entity) {
        return Optional.ofNullable(entity)
            .map(it -> {
                entity.setStatus(UserStatus.REGISTERED);
                entity.setRegisteredAt(LocalDateTime.now());
                return repository.save(entity);
            }).orElseThrow();
    }

    public User login(final String email, final String password) {
        final User user = getUserWithThrow(email, password);
        return user;
    }

    private User getUserWithThrow(final String email, final String password) {
        return repository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
            email,
            password,
            UserStatus.REGISTERED
        ).orElseThrow(() -> new NotFoundException(UserExceptionType.NOT_FOUND_EXCEPTION));
    }

    public User getUserWithThrow(final Long userId) {
        return repository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
            .orElseThrow(() -> new NotFoundException(UserExceptionType.NOT_FOUND_EXCEPTION));
    }

}
