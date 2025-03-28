package com.study.baekjoon.graph.bfs.boj9328;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static char[][] floorPlan = new char[101][101];
    static boolean[] hasKey;
    static boolean[][] visited;
    static int n, m, t;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            visited = new boolean[101][101];
            hasKey = new boolean[26];

            for (int i = 0; i < n; i++) {
                floorPlan[i] = br.readLine().toCharArray();
            }

            String keys = br.readLine();
            for (int i = 0; i < keys.length(); i++) {
                if (keys.charAt(i) == '0') break;
                hasKey[keys.charAt(i) - 'a'] = true;
            }

            Queue<Pair> q = new LinkedList<>();
            List<Pair>[] doors = new List[26];
            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }
            findStartPoint(q, doors);
            sb.append(bfs(q, doors)).append('\n');
        }

        System.out.println(sb.toString());
    }

    private static boolean isKey(int x, int y) {
        int val = floorPlan[x][y] - 'a';

        if (val >= 0 && val < 26) return true;
        return false;
    }

    private static boolean isDoor(int x, int y) {
        int val = floorPlan[x][y] - 'A';

        if (val >= 0 && val < 26) return true;
        return false;
    }

    private static void check(int nx, int ny, Queue<Pair> q, List<Pair>[] doors) {
        if (visited[nx][ny] || floorPlan[nx][ny] == '*') return;

        if (isDoor(nx, ny)) {
            int doorVal = floorPlan[nx][ny] - 'A';
            if (!hasKey[doorVal]) {
                Pair door = new Pair(nx, ny);
                doors[doorVal].add(door);
                return ;
            }
        } else if (isKey(nx, ny)) {
            int keyVal = floorPlan[nx][ny] - 'a';
            hasKey[keyVal] = true;

            List<Pair> door = doors[keyVal];
            for (Pair pair : door) {
                if (visited[pair.x][pair.y]) continue;

                visited[pair.x][pair.y] = true;
                q.offer(new Pair(pair.x, pair.y));
            }
        }

        visited[nx][ny] = true;
        q.offer(new Pair(nx, ny));
    }

    private static int bfs(Queue<Pair> q, List<Pair>[] doors) {
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        int cnt = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (floorPlan[p.x][p.y] == '$') cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                check(nx, ny, q, doors);
            }
        }

        return cnt;
    }

    private static void findStartPoint(Queue<Pair> q, List<Pair>[] doors) {
        for (int j = 0; j < m; j++) {
            check(0, j, q, doors);
            check(n - 1, j, q, doors);
        }

        for (int i = 0; i < n; i++) {
            check(i, 0, q, doors);
            check(i, m - 1, q, doors);
        }
    }

}
