package com.study.baekjoon.dp.boj25822;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] dp = new int[100001];
    static StringBuilder sb = new StringBuilder();
    static float c;
    static int n;

    public static void main(String[] args) throws IOException {
        c = Float.parseFloat(br.readLine());
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int freeze = (int) (c / 0.99);
        if (freeze > 2) freeze = 2;
        int maxSolve = 0;

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1] + (num == 0 ? 1 : 0);
            maxSolve = Math.max(maxSolve, num);
        }

        int left = 1, right = 1;
        int maxStreak = 0;

        while (right <= n) {
            if(dp[right] - dp[left - 1] <= freeze) {
                maxStreak = Math.max(maxStreak, right - left + 1);
                right++;
            } else left++;
        }

        sb.append(maxStreak).append('\n').append(maxSolve);
        bw.write(sb.toString());
        bw.flush();
    }

}
