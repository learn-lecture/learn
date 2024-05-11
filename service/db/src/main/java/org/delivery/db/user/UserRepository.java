package org.delivery.db.user;

import org.delivery.db.user.vo.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);
	UserEntity findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);

}
