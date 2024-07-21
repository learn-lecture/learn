package com.study.baekjoon.divideandconquer.boj10830;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder SB = new StringBuilder();
    static int MATRIX[][];
    static int N;
    static long B;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(BR.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        MATRIX = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(BR.readLine());
            for (int j = 0; j < N; j++) {
                MATRIX[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = rec(B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                SB.append(result[i][j] % 1000).append(" ");
            }
            SB.append("\n");
        }

        BW.write(SB.toString());
        BW.flush();
    }

    private static int[][] rec(long exp) {
        if (exp == 1L) return MATRIX;
        int[][] result = rec(exp / 2);

        result = times(result, result);
        if (exp % 2 == 1L) {
            result = times(result, MATRIX);
        }

        return result;
    }

    private static int[][] times(int[][] original, int[][] op) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += original[i][k] * op[k][j];
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }

}
