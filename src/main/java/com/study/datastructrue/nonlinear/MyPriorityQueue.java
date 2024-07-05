package com.study.datastructrue.nonlinear;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MyPriorityQueue {

	public static void main(String[] args) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// logN
		pq.add(10); 	pq.offer(20);	pq.offer(30);
		pq.offer(40);	pq.offer(50);	pq.offer(60);

		// pop - log
		System.out.println("Poll - Top: " + pq.poll());

		// top - constant
		System.out.println("Peek - Top: " + pq.peek());
		System.out.println("Element - Top: " + pq.element());

		// find - linear + remove - log
		// perform NlogN
		// no arg == poll
		System.out.println("Remove - True/False: " + pq.remove(10));

		print(pq);

		performTest(0);
		performTest(100000);

		maxPqTest();
	}

	private static void print(PriorityQueue<Integer> pq) {
		PriorityQueue<Integer> copy = new PriorityQueue<>(pq);
		while (!copy.isEmpty()) {
			System.out.print(copy.peek() + " ");
			copy.poll();
		}
		System.out.println();
	}

	private static void performTest(int size) {
		if (size < 11) size = 11;
		PriorityQueue<Integer> pq = new PriorityQueue<>(size);

		long startTime, endTime;
		startTime = System.nanoTime();
		for (int i = 0; i < 100000; i++) {
			pq.add(1);
		}
		endTime = System.nanoTime();

		System.out.println("Priority Queue Add Time: " + (endTime - startTime) + " ns");
	}

	private static void maxPqTest() {
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> maxPq2 = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int i = 1; i <= 10; i++) {
			maxPq.add(i);
			maxPq2.add(i);
		}

		print(maxPq);
		print(maxPq2);
	}

}
