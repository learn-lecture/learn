package org.study.user.application;

import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.application.interfaces.UserRepository;
import org.study.user.domain.User;
import org.study.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return  userRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

}
