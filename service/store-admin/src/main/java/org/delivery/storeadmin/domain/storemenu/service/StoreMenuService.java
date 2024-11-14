package org.delivery.storeadmin.domain.storemenu.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.vo.StoreMenuStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenu getStoreMenuWithThrow(Long id) {
        return Optional.ofNullable(
                storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
            )
            .orElseThrow(IllegalArgumentException::new);
    }
}
