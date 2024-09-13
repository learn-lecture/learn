package com.study.baekjoon.bruteforce.recursion.boj2210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr = new int[5][5];
    static boolean[] visited = new boolean[1000000];
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int cnt;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rec(i, j, 0, 0);
            }
        }

        System.out.println(cnt);
    }

    static void rec(int x, int y, int k, int res) {
        if (k == 6) {
            if (visited[res]) return;
            visited[res] = true;
            cnt++;
            return ;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
                continue;
            rec(nx, ny, k + 1, res * 10 + arr[nx][ny]);
        }
    }

}
