package org.delivery.storeadmin.domain.storeuser.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.db.storeuser.StoreUserRepository;
import org.delivery.db.storeuser.vo.StoreUserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public StoreUser register(final StoreUser storeUser) {
        storeUser.setStatus(StoreUserStatus.REGISTERED);
        storeUser.setPassword(passwordEncoder.encode(storeUser.getPassword()));
        storeUser.setRegisteredAt(LocalDateTime.now());

        return storeUserRepository.save(storeUser);
    }

    public Optional<StoreUser> getRegisterUser(final String email) {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }

}
