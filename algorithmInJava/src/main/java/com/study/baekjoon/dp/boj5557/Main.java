package com.study.baekjoon.dp.boj5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr = new int[101];
    static long[][] dp = new long[101][21];
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][arr[1]] = 1;
        for (int i = 1; i < n; i++) {
            for (int sumTarget = 0; sumTarget <= 20 - arr[i]; sumTarget++) {
                dp[i][arr[i] + sumTarget] += dp[i - 1][sumTarget];
            }
            for (int subTarget = arr[i]; subTarget <= 20; subTarget++) {
                dp[i][subTarget - arr[i]] += dp[i - 1][subTarget];
            }
        }

        System.out.println(dp[n - 1][arr[n]]);
    }

}
