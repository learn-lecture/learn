package org.delivery.db.user

import org.delivery.db.user.vo.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(userId: Long?, status: UserStatus?): User?
    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): User?

}