package tobyspring.tobyspringtwo.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Getter
@ToString
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String no;

    private BigDecimal total;

    public Order(final String no, final BigDecimal total) {
        this.no = no;
        this.total = total;
    }
}
