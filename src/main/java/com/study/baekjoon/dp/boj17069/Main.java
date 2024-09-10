package com.study.baekjoon.dp.boj17069;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int HORIZON = 0;
    private static final int VERTICAL = 1;
    private static final int DIAGONAL = 2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][][] home = new long[33][33][3];
    static int[][] wall = new int[33][33];
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i <= n; i++) {
            if (wall[1][i] == 1) break;
            home[1][i][HORIZON] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                // wall
                if (wall[i][j] == 1) continue;
                // horizon
                home[i][j][HORIZON] = home[i][j - 1][DIAGONAL] + home[i][j - 1][HORIZON];
                // vertical
                home[i][j][VERTICAL] = home[i - 1][j][DIAGONAL] + home[i - 1][j][VERTICAL];
                // diagonal
                if (wall[i - 1][j] == 1 || wall[i][j - 1] == 1) continue;
                for (int k = 0; k < 3; k++) {
                    home[i][j][DIAGONAL] += home[i - 1][j - 1][k];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += home[n][n][i];
        }
        System.out.println(sum);
    }

}
