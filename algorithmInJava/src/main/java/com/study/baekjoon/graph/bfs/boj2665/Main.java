package com.study.baekjoon.graph.bfs.boj2665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] rooms = new char[51][51];
    static boolean[][] visited = new boolean[51][51];
    static int[][] changeRooms = new int[51][51];
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int n;

    static class Pair {

        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            rooms[i] = br.readLine().toCharArray();
        }

        Deque<Pair> dq = new LinkedList<>();
        dq.add(new Pair(0, 0));
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            Pair room = dq.pollFirst();

            if (room.x == n - 1 && room.y == n - 1) {
                System.out.println(changeRooms[room.x][room.y]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = room.x + dx[i];
                int ny = room.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (rooms[nx][ny] == '1') {
                    dq.addFirst(new Pair(nx, ny));
                    changeRooms[nx][ny] = changeRooms[room.x][room.y];
                } else {
                    dq.addLast(new Pair(nx, ny));
                    changeRooms[nx][ny] = changeRooms[room.x][room.y] + 1;
                }
                visited[nx][ny] = true;
            }
        }
    }

}
