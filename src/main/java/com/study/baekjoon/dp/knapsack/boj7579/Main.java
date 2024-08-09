package com.study.baekjoon.dp.knapsack.boj7579;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] dp = new int[101][10001];
    static int[] byt2 = new int[101];
    static int[] cost = new int[101];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            byt2[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int totalCost = 0;
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalCost; j++) {
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j] + dp[i - 1][j - cost[i]] + byt2[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for (int i = 0; i <= totalCost; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }

}
