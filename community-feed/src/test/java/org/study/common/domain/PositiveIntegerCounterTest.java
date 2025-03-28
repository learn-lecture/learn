package org.study.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrement_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increment();

        // then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncrement_whenDecrement_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increment();

        // when
        counter.decrement();

        // then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrement_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        // when
        counter.decrement();

        // then
        assertEquals(0, counter.getCount());
    }

}