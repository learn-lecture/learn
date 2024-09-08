package org.study.user.application.interfaces;

import java.util.Optional;
import org.study.user.domain.User;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);

}
