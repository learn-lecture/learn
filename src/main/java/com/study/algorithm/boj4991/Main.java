package com.study.algorithm.boj4991;

import java.io.*;
import java.util.*;

class Pair {

    int x;
    int y;
    int cnt;

    Pair (int x, int y, int cnt) {
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
    static boolean[][] visited = new boolean[21][21];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M;
    static Pair SPOT = new Pair(0, 0, 0);

    public static void main(String[] args) throws IOException {
        while (input()) {
            Pair spot = bfs(SPOT);
            if (Objects.equals(SPOT, spot)) {
                SB.append(-1);
                continue;
            }
            spot.cnt += SPOT.cnt;
            SPOT = spot;

            for (int i = 0; i < 9; i++) {
                spot = bfs(SPOT);
                if (Objects.equals(SPOT, bfs(SPOT))) {
                    break;
                }
                spot.cnt += SPOT.cnt;
                SPOT = spot;
            }
            SB.append(SPOT.cnt).append('\n');
        }
        BW.write(SB.toString());
        BW.flush();
        BW.close();
        BR.close();
    }

    private static Pair bfs(Pair start) {
        for (boolean vis[]: visited) {
            Arrays.fill(vis, false);
        }

        Queue<Pair> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (ROOM[p.x][p.y] == '*') {
                ROOM[p.x][p.y] = '.';
                return p;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (ROOM[nx][ny] == 'x') continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny, p.cnt + 1));
            }
        }

        return start;
    }

    private static boolean input() throws IOException {
        StringTokenizer st = new StringTokenizer(BR.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        if (M == 0 && N == 0) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            String info = BR.readLine();
            for (int j = 0; j < info.length(); j++) {
                if (info.charAt(j) == 'o') {
                    ROOM[i][j] = '.';
                    SPOT.x = i;
                    SPOT.y = j;
                    SPOT.cnt = 0;
                } else {
                    ROOM[i][j] = info.charAt(j);
                }
            }
        }
        return true;
    }

}
