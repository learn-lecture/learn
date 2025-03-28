package com.study.organize;

import java.util.PriorityQueue;

public class LearnPriorityQueue {

    public static void main(String[] args) {
        // 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(2);
        final Integer peek = pq.peek();
        System.out.println(peek);
        pq.poll();

        // 최대 힙
        pq = new PriorityQueue<>((x, y) -> y - x);
        pq.offer(1);
        pq.offer(2);
        System.out.println(pq.peek());

        pq.remove(1);
        pq.poll();
        System.out.println(pq.poll());

        pq.peek();
    }

}
