package com.study.baekjoon.dp.boj15486;

import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX_SIZE = 1500001;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] term = new int[MAX_SIZE];
    static int[] pay = new int[MAX_SIZE];
    static int[] dp = new int[MAX_SIZE];
    static StringTokenizer st;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            term[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {
            int next = i + term[i];
            if (next > n) dp[i] = dp[i + 1];
            else dp[i] = Math.max(dp[i + 1], pay[i] + dp[next]);
        }

        System.out.println(dp[0]);
    }

}
