package com.study.baekjoon.dp.boj1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] dp = new long[101][101];
    static int[][] board = new int[101][101];
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == 0 || board[i][j] == 0) continue;
                int right = j + board[i][j];
                int down = i + board[i][j];

                if (right <= n) dp[i][right] += dp[i][j];
                if (down <= n) dp[down][j] += dp[i][j];
            }
        }

        System.out.println(dp[n][n]);
    }
}
