package com.study.baekjoon.disjointset.boj16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] graph = new char[1001][1001];
    static int[][] visited = new int[1001][1001];
    static int n, m, cnt;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    cnt = dfs(i, j) > cnt ? cnt + 1 : cnt;
                }
            }
        }

        System.out.println(cnt);
    }

    static int dfs(int x, int y) {
        if (visited[x][y] != 0) return visited[x][y];

        visited[x][y] = cnt + 1;
        if (graph[x][y] == 'D') return visited[x][y] = dfs(x + 1, y);
        else if (graph[x][y] == 'L') return visited[x][y] = dfs(x, y - 1);
        else if (graph[x][y] == 'R') return visited[x][y] = dfs(x, y + 1);

        return visited[x][y] = dfs(x - 1, y);
    }

}