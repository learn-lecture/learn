package com.study.baekjoon.dp.boj2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<Integer> coins = new TreeSet<>();
    static int[] dp = new int[10001];
    static int n, k;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin > k) continue;
            coins.add(coin);
        }

        for (int i = 1; i <= 10000; i++) {
            dp[i] = 10005;
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[k] == 10005) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }

}

/*
* 1
* 11
* 111
* 1111
* 11111 5
* 111111 51
* 1111111 511
* 11111111 5111
* 111111111 51111
* 1111111111 511111 55
* 11111111111 5111111 551
* 111111111111 51111111 5511 12
* */