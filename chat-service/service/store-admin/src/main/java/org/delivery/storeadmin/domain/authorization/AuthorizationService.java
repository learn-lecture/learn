package org.delivery.storeadmin.domain.authorization;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.db.store.Store;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.vo.StoreStatus;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override
    public UserSession loadUserByUsername(final String username) throws UsernameNotFoundException {
        StoreUser user = storeUserService.getRegisterUser(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        Store store = Optional.ofNullable(
                storeRepository.findFirstByIdAndStatusOrderByIdDesc(user.getStoreId(), StoreStatus.REGISTERED))
            .orElseThrow(() -> new IllegalArgumentException(username));

        return UserSession.builder().userId(user.getId()).email(user.getEmail()).password(user.getPassword())
            .status(user.getStatus()).role(user.getRole()).registeredAt(user.getRegisteredAt())
            .unregisteredAt(user.getUnregisteredAt()).lastLoginAt(user.getLastLoginAt()).storeId(store.getId())
            .storeName(store.getName()).build();
    }

}
