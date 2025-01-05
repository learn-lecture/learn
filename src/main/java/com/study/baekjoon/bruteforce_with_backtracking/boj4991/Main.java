package com.study.baekjoon.bruteforce_with_backtracking.boj4991;

import java.io.*;
import java.util.*;

class Pair {
    int x;
    int y;
    int cnt;

    Pair(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder SB = new StringBuilder();
    static char[][] ROOM = new char[21][21];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M, DIRTY_COUNT;
    static Pair START;
    static int TOTAL_DISTANCE;

    public static void main(String[] args) throws IOException {

        while (input()) {
            TOTAL_DISTANCE = Integer.MAX_VALUE;
            rec(START, 0);
            DIRTY_COUNT = 0;
            if (TOTAL_DISTANCE == Integer.MAX_VALUE) {
                TOTAL_DISTANCE = -1;
            }
            SB.append(TOTAL_DISTANCE).append("\n");
        }

        BW.write(SB.toString());
        BW.flush();
        BW.close();
        BR.close();
    }

    private static void rec(Pair start, int k) {
        if (k == DIRTY_COUNT) {
            TOTAL_DISTANCE = Math.min(TOTAL_DISTANCE, start.cnt);
        }


        boolean[][] visited = new boolean[21][21];
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if(p.cnt >= TOTAL_DISTANCE) return ;

            if (ROOM[p.x][p.y] == '*') {
                ROOM[p.x][p.y] = '.';
                rec(p, k + 1);
                ROOM[p.x][p.y] = '*';
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                if (ROOM[nx][ny] == 'x') continue;
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny, p.cnt + 1));
            }
        }
    }

    private static boolean input() throws IOException {
        StringTokenizer st = new StringTokenizer(BR.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        if (N == 0 && M == 0) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            String info = BR.readLine();
            for (int j = 0; j < M; j++) {
                ROOM[i][j] = info.charAt(j);
                if (ROOM[i][j] == 'o') {
                    START = new Pair(i, j, 0);
                    ROOM[i][j] = '.';
                }
                if (ROOM[i][j] == '*') {
                    DIRTY_COUNT++;
                }
            }
        }
        return true;
    }

}