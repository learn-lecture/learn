package tobyspring.tobyspringtwo.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
public class Order {

    @Setter
    private Long id;

    private String no;

    private BigDecimal total;

    public Order(final String no, final BigDecimal total) {
        this.no = no;
        this.total = total;
    }

}
