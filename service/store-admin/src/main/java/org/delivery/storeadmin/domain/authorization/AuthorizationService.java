package org.delivery.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        StoreUser storeUser = storeUserService.getRegisterUser(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return User.builder()
            .username(storeUser.getEmail())
            .password(storeUser.getPassword())
            .roles(storeUser.getRole().toString())
            .build();
    }

}
