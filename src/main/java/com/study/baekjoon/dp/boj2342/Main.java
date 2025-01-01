package com.study.baekjoon.dp.boj2342;

import java.io.*;
import java.util.*;

/*  acmicpc 2342
*   DanceDance Revolution
*   25.01.01 PM 10:59 ~ 11:25
* */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> steps = new ArrayList<>(100005);
    static int[][][] dp = new int[5][5][100005];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        while (true) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                break;
            }

            steps.add(num);
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(solve(0, 0, 0));
    }

    static int solve(int left, int right, int step) {
        if(step == steps.size()) return 0;
        if(dp[left][right][step] != -1) return dp[left][right][step];

        int next = steps.get(step);
        int result = Math.min(
                solve(next, right, step + 1) + getPower(left, next),
                solve(left, next, step + 1) + getPower(right, next)
        );

        return dp[left][right][step] = result;
    }

    private static int getPower(int from, int to) {
        if (from == to) return 1;
        if (from == 0) return 2;
        if (Math.abs(from - to) == 2) return 4;
        return 3;
    }

}
