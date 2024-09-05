package com.study.baekjoon.dp.boj11058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static long[] dp = new long [101];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 3, k = i - 4; j <= i - 3; j++, k--) {
                dp[i] = Math.max(dp[i], dp[j] * k);
            }
        }
        System.out.println(dp[n]);
    }

}