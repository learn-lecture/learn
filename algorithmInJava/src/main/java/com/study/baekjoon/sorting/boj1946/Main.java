package com.study.baekjoon.sorting.boj1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, t;

    static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            List<Pair> info = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int doc = Integer.parseInt(st.nextToken());
                int itv = Integer.parseInt(st.nextToken());
                info.add(new Pair(doc, itv));
            }
            Collections.sort(info);

            int cnt = 1;
            int minRank = info.get(0).y;
            for (int i = 1; i < n; i++) {
                if (info.get(i).y < minRank) {
                    cnt++;
                    minRank = info.get(i).y;
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb.toString());
    }
}
