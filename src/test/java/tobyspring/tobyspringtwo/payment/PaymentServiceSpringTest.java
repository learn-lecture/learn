package tobyspring.tobyspringtwo.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.tobyspringtwo.TestPaymentConfig;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ExRateProviderStub exRateProviderStub;
    @Autowired
    private Clock clock;


    @Test
    @DisplayName("prepare method 가 요구사항 3가지를 충족했는지 검증")
    void convertedAmount() {
        Payment prepare = paymentService.prepare(1L, "USD", TEN);

        assertThat(prepare.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(prepare.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        exRateProviderStub.setExRate(valueOf(500));
        Payment prepare2 = paymentService.prepare(1L, "USD", TEN);

        assertThat(prepare2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(prepare2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        Assertions.assertThat(now.plusMinutes(30)).isEqualTo(payment.getValidUntil());
    }

}