package org.study.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DatetimeInfo info = new DatetimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDateTime();

        // then
        assertNotEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }

}