package tobyspring.tobyspringtwo.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare method 가 요구사항 3가지를 충족했는지 검증")
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1000), valueOf(10_000));
        testAmount(valueOf(3000), valueOf(30_000));

        // 원화환산금액 유효시간 계산
    //    assertThat(prepare.getValidUntil()).isAfter(LocalDateTime.now());
    //    assertThat(prepare.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
        Payment prepare = paymentService.prepare(1L, "USD", TEN);

        // 환율 정보를 가져온다.
        assertThat(prepare.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(prepare.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}