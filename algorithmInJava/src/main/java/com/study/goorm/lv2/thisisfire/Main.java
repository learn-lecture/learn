package com.study.goorm.lv2.thisisfire;

import java.io.*;
import java.util.*;

class Main {

    static char[][] lab = new char[1001][1001];
    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    static class Pair {
        int x, y, c;
        Pair(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String info = br.readLine();
            for (int j = 0; j < m; j++) {
                lab[i][j] = info.charAt(j);
                if (lab[i][j] == '@') {
                    q.offer(new Pair(i, j, 0));
                }
            }
        }

        System.out.print(multisourceBfs(n, m));
    }

    static int multisourceBfs(int n, int m) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (lab[nx][ny] == '@' || lab[nx][ny] == '#') continue;
                if (lab[nx][ny] == '&') return p.c;
                lab[nx][ny] = '@';
                q.offer(new Pair(nx, ny, p.c + 1));
            }
        }
        return -1;
    }

}