package com.study.baekjoon.dp.boj15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] dp = new int[10001][4];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp[1][1] = dp[2][1] = dp[2][2] = dp[3][3] = 1;
        dp[3][1] = 2;
        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i-1][1] + dp[i-1][2] + dp[i-1][3];
            dp[i][2] = dp[i-2][2] + dp[i-2][3];
            dp[i][3] = dp[i-3][3];
        }

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num][1] + dp[num][2] + dp[num][3]).append("\n");
        }

        System.out.println(sb);
    }

}
/*
1* 1
2* 11, 2
3* 111, 21, 3
4* 1111, 211, 31, 22
5* 11111, 2111, 311, 221, 23
6* 111111, 21111, 3111, 2211, 231, 222, 33
7* 1111111, 211111, 311111, 22111, 3211, 2221, 331, 322
8* 11111111, 2111111, 3111111, 221111, 32111, 22211, 3311, 3221, 2222, 332
* */