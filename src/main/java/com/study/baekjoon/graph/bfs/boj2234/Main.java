package com.study.baekjoon.graph.bfs.boj2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] castle = new int[51][51];
    static int[][] visited = new int[51][51];
    static int[] roomSize = new int[250], dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int allRoomsCount = findAllRoomCount();
        int maxRoomSize = 0;
        for (int i = 1; i <= allRoomsCount; i++) {
            maxRoomSize = Math.max(maxRoomSize, roomSize[i]);
        }
        int maxSumRoomSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    if ((castle[i][j] & (1 << k)) == 0) continue;
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (visited[nx][ny] != visited[i][j]) {
                        int room1 = roomSize[visited[i][j]];
                        int room2 = roomSize[visited[nx][ny]];
                        maxSumRoomSize = Math.max(maxSumRoomSize, room1 + room2);
                    }
                }
            }
        }
        sb.append(allRoomsCount).append('\n').append(maxRoomSize).append('\n').append(maxSumRoomSize);
        System.out.println(sb);
    }

    static int findAllRoomCount() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] > 0) continue;
                cnt++;
                roomSize[cnt] = bfs(i, j, cnt);
            }
        }
        return cnt;
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(int x, int y, int cnt) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = cnt;
        int size = 0;

        while (!q.isEmpty()) {
            Pair now = q.poll();
            size++;
            for (int i = 0; i < 4; i++) {
                if ((castle[now.x][now.y] & (1 << i)) > 0) continue;
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (visited[nx][ny] > 0) continue;
                visited[nx][ny] = cnt;
                q.offer(new Pair(nx, ny));
            }
        }

        return size;
    }

}
