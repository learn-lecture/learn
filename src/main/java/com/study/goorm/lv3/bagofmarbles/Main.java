package com.study.goorm.lv3.bagofmarbles;

import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int x;
        int idx;

        public Pair(int x, int idx) {
            this.x = x;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.x, other.x);
        }
    }

    static Pair[] list = new Pair[200005];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            list[i] = new Pair(num, i);
            sum += num;
        }

        Arrays.sort(list, 0, n);
        List<Integer> result = new LinkedList();

        for (int i = 0; i < n; i++) {
            Pair last = (i == n - 1) ? list[n - 2] : list[n - 1];
            long temp = sum - list[i].x;

            if (last.x == temp - last.x) result.add(list[i].idx + 1);
        }

        Collections.sort(result);
        sb.append(result.size()).append('\n');
        for (int x : result) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }

}