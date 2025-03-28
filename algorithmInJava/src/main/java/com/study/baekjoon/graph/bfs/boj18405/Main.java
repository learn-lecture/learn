package com.study.baekjoon.graph.bfs.boj18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] testTube = new int[201][201];
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static StringTokenizer st;
    static int n, k, s, x, y;

    static class Tuple implements Comparable<Tuple> {
        int x, y, time;

        public Tuple(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Tuple o) {
            if (this.time == o.time) {
                return testTube[this.x][this.y] - testTube[o.x][o.y];
            }
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                testTube[i][j] = Integer.parseInt(st.nextToken());
                if (testTube[i][j] > 0) {
                    pq.offer(new Tuple(i, j, 0));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        while (!pq.isEmpty()) {
            Tuple t = pq.poll();
            if (t.time > s) {
                testTube[t.x][t.y] = 0;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (testTube[nx][ny] > 0) continue;

                testTube[nx][ny] = testTube[t.x][t.y];
                pq.offer(new Tuple(nx, ny, t.time + 1));
            }
        }

        System.out.println(testTube[x - 1][y - 1]);
    }

}
