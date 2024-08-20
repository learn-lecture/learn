package com.study.baekjoon.greedy.boj11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;

    static class Pair implements Comparable<Pair> {
        int s, e;

        public Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.s == o.s) {
                return this.e - o.e;
            }
            return this.s - o.s;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        List<Pair> lessons = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            lessons.add(new Pair(startTime, endTime));
        }

        Collections.sort(lessons);

        for (Pair lesson : lessons) {
            pq.offer(lesson.e);
            if (pq.peek() <= lesson.s) {
                pq.poll();
            }
        }
        System.out.println(pq.size());
    }

}
