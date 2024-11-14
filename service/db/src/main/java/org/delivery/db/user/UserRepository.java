/*
package org.delivery.db.user;

import java.util.Optional;

import org.delivery.db.user.vo.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);
	Optional<User> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);

}
*/
