package com.study.baekjoon.dp.boj2293;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer ST;
    static int[] DP = new int[10001];
    static int N, K;

    public static void main(String[] args) throws IOException {
        ST = new StringTokenizer(BR.readLine());
        N = Integer.parseInt(ST.nextToken());
        K = Integer.parseInt(ST.nextToken());

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(BR.readLine());
            if (number > K) continue;
            DP[number]++;

            for (int start = number + 1; start <= K; start++) {
                DP[start] += DP[start - number];
            }
        }

        System.out.println(DP[K]);
    }
}
