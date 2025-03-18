package com.study.organize;

import java.util.*;

public class LearnDeque {

    public static void main(String[] args) {
        // 얘는 맨 앞에 삽입 해야 될 때도 빠름
        Deque<Integer> arrayDeque = new LinkedList<>();
        arrayDeque.addFirst(1);
        arrayDeque.addLast(1);
        arrayDeque.peek();
        System.out.println(arrayDeque.size());
        arrayDeque.pop(); // removeFirst
        arrayDeque.removeLast();
        arrayDeque.size();
        arrayDeque.add(3);
        for (int a: arrayDeque) {
            System.out.println(a);
        }

        arrayDeque = new ArrayDeque<>(); // 위랑 같음.
        arrayDeque.add(3);
        for (int a: arrayDeque) {
            System.out.println(a);
        }
    }

}
