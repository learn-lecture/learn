package org.delivery.storeadmin.domain.storemenu.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.delivery.db.storemenu.vo.StoreMenuStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StoreMenuResponse {

    private Long id;
    private String name;
    private BigDecimal amount;
    private StoreMenuStatus status;
    private String thumbnailUrl;
    private int likeCount;
    private int sequence;

}
