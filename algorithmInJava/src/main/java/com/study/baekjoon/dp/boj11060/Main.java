package com.study.baekjoon.dp.boj11060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr = new int[1001];
    static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            dp[i] = -1;
        }

        dp[1] = 0;

        for (int i = 1; i <= n; i++) {
            if (dp[i] == -1) continue;
            for (int jump = 1; jump <= arr[i]; jump++) {
                int next = i + jump;
                if (next > n) continue;
                if (dp[next] == -1) dp[next] = dp[i] + 1;
                else {
                    dp[next] = Math.min(dp[next], dp[i] + 1);
                }
            }
        }

        System.out.println(dp[n]);
    }

}
