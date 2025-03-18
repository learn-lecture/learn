package com.study.baekjoon.graph.bfs.boj9376;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, t;
    static StringTokenizer st;
    static StringBuilder sb;
    static char[][] prison = new char[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static class Convict implements Comparable<Convict> {
        int x, y, open;
        Convict(int x, int y, int open) {
            this.x = x;
            this.y = y;
            this.open = open;
        }

        public int compareTo(Convict o) {
            return open - o.open;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            prison[i] = br.readLine().toCharArray();
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (prison[i][j] == '$') {
                    visited[i][j] = true;
                    cnt += bfs(i, j);
                    System.out.println(cnt);
                    for (int k = 0; k < n; k++) Arrays.fill(visited[k], false);
                }
            }
        }
        System.out.println(cnt);
    }

    static int bfs(int x, int y) {
        PriorityQueue<Convict> pq = new PriorityQueue<>();
        pq.offer(new Convict(x, y, 0));

        while (!pq.isEmpty()) {
            Convict convict = pq.poll();

            System.out.println(convict.x + ", " + convict.y + ", " + convict.open);
            if (canEscape(convict)) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print(visited[i][j] + " ");
                    }
                    System.out.println();
                }
                return convict.open;
            }

            int open = convict.open;
            for (int i = 0; i < 4; i++) {
                int nx = convict.x + dx[i], ny = convict.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (prison[nx][ny] == '*' || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (prison[nx][ny] == '#') {
                    prison[nx][ny] = '.';
                    pq.offer(new Convict(nx, ny, open + 1));
                } else {
                    pq.offer(new Convict(nx, ny, open));
                }
            }
        }

        return 0;
    }

    private static boolean canEscape(Convict convict) {
        return convict.x == 0 || convict.y == 0 || convict.x == n - 1 || convict.y == m - 1;
    }

}
