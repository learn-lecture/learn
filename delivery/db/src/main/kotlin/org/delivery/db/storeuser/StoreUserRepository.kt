package org.delivery.db.storeuser

import org.delivery.db.storeuser.vo.StoreUserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreUserRepository : JpaRepository<StoreUser, Long> {

    fun findFirstByEmailAndStatusOrderByIdDesc(email: String?, status: StoreUserStatus?): StoreUser?

}
