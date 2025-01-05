package com.study.baekjoon.dp_with_bitmask.boj1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][][] dp = new int[101][10][(1 << 10)];
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        for (int startAndEndWithNumber = 1; startAndEndWithNumber < 10; startAndEndWithNumber++) {
            dp[1][startAndEndWithNumber][(1 << startAndEndWithNumber)] = 1;
        }

        for (int digit = 2; digit <= n; digit++) {
            for (int stairNum = 0; stairNum < 10; stairNum++) {
                for (int bit = 0; bit < 1024; bit++) {
                    int now = dp[digit][stairNum][bit | (1 << stairNum)];
                    if (stairNum > 0) now = (now + dp[digit - 1][stairNum - 1][bit]) % 1000000000;
                    if (stairNum < 9) now = (now + dp[digit - 1][stairNum + 1][bit]) % 1000000000;
                    dp[digit][stairNum][bit | (1 << stairNum)] = now;
                }
            }
        }

        int sum = 0;
        for (int startAndEndWithNumber = 0; startAndEndWithNumber < 10; startAndEndWithNumber++) {
            sum = (sum + dp[n][startAndEndWithNumber][1023]) % 1000000000;
        }

        System.out.println(sum);
    }

}