package org.delivery.db.storemenu;

import java.util.List;
import java.util.Optional;

import org.delivery.db.storemenu.vo.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {

	Optional<StoreMenu> findFirstByIdAndStatusOrderByIdDesc(final Long id, final StoreMenuStatus status);
	List<StoreMenu> findAllByStoreIdAndStatusOrderBySequenceDesc(final Long sotreId, final StoreMenuStatus status);

}
