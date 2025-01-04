package com.study.baekjoon.disjointset.boj20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* acmicpc 20040, 사이클 게임
* 25.01.04 PM 10:07 ~ 10:19
* */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent = new int[500005];
    static StringTokenizer st;
    static int n, m;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        System.out.println(solve());
    }

    private static int solve() throws IOException {
        for (int round = 1; round <= m; round++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (isCycle(x, y)) return round;
        }
        return 0;
    }

    private static boolean isCycle(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
            return false;
        }

        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        return parent[x] = find(parent[x]);
    }

}
