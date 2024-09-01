package org.study.common.domain;

public class PositiveIntegerCounter {

    int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increment() {
        this.count++;
    }

    public void decrement() {
        if (this.count <= 0) {
            return;
        }
        this.count--;
    }

}
