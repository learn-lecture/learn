/*
package org.delivery.db.store;

import java.util.List;
import java.util.Optional;
import org.delivery.db.store.vo.StoreCategory;
import org.delivery.db.store.vo.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findFirstByIdAndStatusOrderByIdDesc(final Long id, final StoreStatus status);

    List<Store> findAllByStatusOrderByIdDesc(final StoreStatus status);

    List<Store> findAllByStatusAndCategoryOrderByStarDesc(final StoreStatus status, final StoreCategory category);

    Optional<Store> findFirstByNameAndStatusOrderByIdDesc(final String name, final StoreStatus status);

}
*/
