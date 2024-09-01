package org.study.user.domain;

public class UserRelationCounter {

    int count;

    public UserRelationCounter() {
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
