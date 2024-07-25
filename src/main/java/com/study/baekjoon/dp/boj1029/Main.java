package com.study.baekjoon.dp.boj1029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer ST;
    static int N, RESULT;
    static int[][] map = new int[16][16];
    static int[][] DP = new int[1<<15][16];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(BR.readLine());
        for (int i = 1; i <= N; i++) {
            String input = BR.readLine();
            for (int j = 0; j < input.length(); j++) {
                map[i][j+1] = input.charAt(j) - '0';
            }
        }

        dfs(1, 1, 1);
        System.out.println(RESULT);
    }

    public static void dfs(int i , int state, int visitCnt) {
        if (RESULT == N) return;

        RESULT = Math.max(RESULT, visitCnt);
        if (state == (1 << N) - 1) return;

        for (int j = 1; j <= N; ++j) {
            int bit = (state & (1 << (j - 1)));
            if (bit == 0 && map[i][j] >= DP[state][i]) {
                int comBit = (1 << (j - 1));
                if (DP[state | comBit][j] != 0 && DP[state | comBit][j] <= map[i][j]) continue;
                DP[state | comBit][j] = map[i][j];
                dfs(j, state | comBit, visitCnt + 1);
            }
        }
    }

}
