package com.study.organize;

import java.util.LinkedList;
import java.util.Queue;

public class LearnQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // push
        queue.offer(2);
        System.out.println(queue.peek());
        System.out.println(queue.size());
        queue.poll(); // remove & peek
        System.out.println(queue.size());
    }

}
