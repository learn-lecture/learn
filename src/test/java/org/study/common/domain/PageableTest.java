package org.study.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PageableTest {

    @Test
    void givenPageableIndex_whenGetOffset_thenShouldBeReturn0() {
        // given
        Pageable pageable = new Pageable();

        // when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // then
        assertEquals(offset, 0);
        assertEquals(limit, 10);
    }

    @Test
    void givenPageableIndexIs2SizeIs10_whenGetOffset_thenShouldBeReturn10() {
        // given
        Pageable pageable = new Pageable(2, 10);

        // when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // then
        assertEquals(offset, 10);
        assertEquals(limit, 10);
    }

}
