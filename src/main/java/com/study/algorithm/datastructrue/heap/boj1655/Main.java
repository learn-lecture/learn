package com.study.algorithm.datastructrue.heap.boj1655;

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder SB = new StringBuilder();

	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> minPq = new PriorityQueue<>(100000);
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(100000, Comparator.reverseOrder());

		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(BR.readLine());
			int num = Integer.parseInt(st.nextToken());

			if (minPq.size() == maxPq.size()) {
				maxPq.offer(num);
			} else {
				minPq.offer(num);
			}

			if (!minPq.isEmpty() && !maxPq.isEmpty()) {
				if (minPq.peek() < maxPq.peek()) {
					int temp = maxPq.poll();
					maxPq.offer(minPq.poll());
					minPq.offer(temp);
				}
			}

			SB.append(maxPq.peek() + "\n");
		}

		BW.write(SB.toString());
		BR.close();
		BW.close();
	}

}
