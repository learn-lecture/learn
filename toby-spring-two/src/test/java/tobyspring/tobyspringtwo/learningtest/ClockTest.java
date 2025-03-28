package tobyspring.tobyspringtwo.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;

import static java.lang.Thread.sleep;

public class ClockTest {
    // Clock을 이용해서 LocalDateTime.now ?
    @Test
    void clock() throws InterruptedException {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime t1 = LocalDateTime.now(clock);
        sleep(1);   // 컴퓨터 성능 이슈로 1ms sleep
        LocalDateTime t2 = LocalDateTime.now(clock);
        Assertions.assertThat(t2).isAfter(t1);
    }
    // Clock을 Test에서 사용할 때 원하는 시간을 지정해서 현재 시간을 가져올 수 있는가?

    @Test
    void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime t1 = LocalDateTime.now(clock);
        LocalDateTime t2 = LocalDateTime.now(clock);
        LocalDateTime t3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(t2).isEqualTo(t1);
        Assertions.assertThat(t3).isEqualTo(t1.plusHours(1));
    }

}
