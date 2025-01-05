package com.study.baekjoon.dp_with_dijointset.boj20303;

import java.io.*;
import java.util.*;

/*
* acmicpc 20303, 할로윈의 양아치
* 25.01.05 PM 10:54 ~
* */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int parent[] = new int[30005];
    static int candyCount[] = new int[30005];
    static StringTokenizer st;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            candyCount[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        System.out.println(solve());
    }

    private static int solve() {
        Map<Integer, Integer> candyCountByGroup = new HashMap<>();
        Map<Integer, Integer> groupCount = new HashMap<>();

        for(int i = 1; i <= n; i++) {
            int root = find(i);
            candyCountByGroup.put(root, candyCountByGroup.getOrDefault(root, 0) + candyCount[i]);
            groupCount.put(root, groupCount.getOrDefault(root, 0) + 1);
        }

        int[] dp = new int[k];
        for(Map.Entry<Integer, Integer> group : candyCountByGroup.entrySet()) {
            int root = group.getKey();
            int candy = group.getValue();
            int count = groupCount.get(root);

            for(int i = k - 1; i >= count; i--) {
                dp[i] = Math.max(dp[i], dp[i - count] + candy);
            }
        }

        return dp[k-1];
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

}
