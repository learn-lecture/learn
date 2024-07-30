package com.study.baekjoon.simulation.boj17144;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] room = new int[51][51];
    static int[][] temp = new int[51][51];
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static StringTokenizer st;
    static int n, m, t;

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        Pair firstCleaner = null;
        Pair secondCleaner = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (firstCleaner == null) {
                        firstCleaner = new Pair(i, j);
                    } else {
                        secondCleaner = new Pair(i, j);
                    }
                }
            }
        }

        while (t-- > 0) {
            spread(firstCleaner, secondCleaner);
            firstClean(firstCleaner);
            secondClean(secondCleaner);
        }

        int totalDust = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] > 0) totalDust += room[i][j];
            }
        }
        System.out.println(totalDust);
    }

    private static Queue<Pair> findDusts() {
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] > 0) {
                    q.offer(new Pair(i, j));
                }
            }
        }
        return q;
    }

    private static void spread(Pair firstCleaner, Pair secondCleaner) {
        Queue<Pair> q = findDusts();
        while (!q.isEmpty()) {
            Pair dust = q.poll();

            int sumOfSpread = 0;
            int extent = room[dust.x][dust.y] / 5;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + dust.x;
                int ny = dy[i] + dust.y;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (room[nx][ny] < 0) continue;

                temp[nx][ny] += extent;
                sumOfSpread += extent;
            }

            temp[dust.x][dust.y] += (room[dust.x][dust.y] - sumOfSpread);
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] = temp[i][j];
                temp[i][j] = 0;
            }
        }
        room[firstCleaner.x][firstCleaner.y] = room[secondCleaner.x][secondCleaner.y] = -1;
    }

    private static void firstClean(Pair firstCleaner) {
        int dust = 0;
        int y = firstCleaner.y + 1;
        int x = firstCleaner.x;

        for (;y < m; y++) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        y -= 1;
        x -= 1;

        for (; x >= 0; x--) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        x += 1;
        y -= 1;

        for (; y >= firstCleaner.y; y--) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        y += 1;
        x += 1;

        for (; x < firstCleaner.x; x++) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
    }

    private static void secondClean(Pair secondCleaner) {


        int dust = 0;
        int y = secondCleaner.y + 1;
        int x = secondCleaner.x;

        for (;y < m; y++) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        y -= 1;
        x += 1;

        for (; x < n; x++) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        x -= 1;
        y -= 1;

        for (; y >= secondCleaner.y; y--) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
        y += 1;
        x -= 1;

        for (; x > secondCleaner.x; x--) {
            int temp = room[x][y];
            room[x][y] = dust;
            dust = temp;
        }
    }

}
