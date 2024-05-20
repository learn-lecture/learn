package org.delivery.db.user;

import java.util.Optional;

import org.delivery.db.user.vo.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);
	Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);

}
