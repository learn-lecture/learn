package com.study.baekjoon.dp.fibonacci.boj15624;

import java.io.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(BR.readLine());
        dp[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        System.out.println(dp[n]);
    }

}