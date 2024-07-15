package tobyspring.tobyspringtwo.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setup() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare method 가 요구사항 3가지를 충족했는지 검증")
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1000), valueOf(10_000), this.clock);
        testAmount(valueOf(3000), valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        Assertions.assertThat(now.plusMinutes(30)).isEqualTo(payment.getValidUntil());
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment prepare = paymentService.prepare(1L, "USD", TEN);

        // 환율 정보를 가져온다.
        assertThat(prepare.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(prepare.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}