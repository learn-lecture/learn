package org.delivery.db.store

import org.delivery.db.store.vo.StoreCategory
import org.delivery.db.store.vo.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): Store?

    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<Store>

    fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<Store>

    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): Store?

}
