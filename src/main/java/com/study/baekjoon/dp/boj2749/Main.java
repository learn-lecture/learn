package com.study.baekjoon.dp.boj2749;

import java.io.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int period = 15;
        for (int i = 0; i < 5; i++) period *= 10;
        int n = (int)(Long.parseLong(BR.readLine()) % period);

        int[] dp = new int[period + 1];

        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }

        System.out.println(dp[n]);
    }

}