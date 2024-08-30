package com.study.baekjoon.dp.boj11048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                int maxCandy = Math.max(dp[i - 1][j - 1], dp[i][j - 1]);
                maxCandy = Math.max(maxCandy, dp[i - 1][j]);
                dp[i][j] = maxCandy + Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dp[n][m]);
    }

}
