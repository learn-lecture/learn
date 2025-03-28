    package com.study.baekjoon.sorting.boj2075;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.PriorityQueue;
    import java.util.StringTokenizer;

    public class Main {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;
        static int n;

        public static void main(String[] args) throws IOException {
            n = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    pq.offer(Integer.parseInt(st.nextToken()));
                    if (pq.size() > n) {
                        pq.poll();
                    }
                }
            }

            System.out.println(pq.peek());
        }

    }
