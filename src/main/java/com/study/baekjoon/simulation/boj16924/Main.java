package com.study.baekjoon.simulation.boj16924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static char[][] board = new char[101][101];
    static boolean[][] doNotDrawHere = new boolean[101][101];
    static StringTokenizer st;

    static class FindCross {
        int x, y, size;
        public FindCross(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int minCrossSize = (Math.min(n, m) - 1) / 2;
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '*') {
                    doNotDrawHere[i][j] = true;
                }
            }
        }

        List<FindCross> crosses = new ArrayList<>(1000);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.') continue;
                for (int drawRange = minCrossSize; drawRange > 0; drawRange--) {
                    if (canDraw(i, j, drawRange)) {
                        overwritten(i, j, drawRange);
                        crosses.add(new FindCross(i + 1, j + 1, drawRange));
                        break;
                    }
                }
            }
        }

        if (canMakeBoard()) {
            StringBuilder sb = new StringBuilder();
            sb.append(crosses.size()).append('\n');
            for (FindCross cross : crosses) {
                sb.append(cross.x).append(' ')
                    .append(cross.y).append(' ')
                    .append(cross.size).append('\n');
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean canDraw(int i, int j, int drawRange) {
        for (int col = j - drawRange; col <= j + drawRange; col++) {
            if (col < 0 || col >= m || board[i][col] == '.') return false;
        }
        for (int row = i - drawRange; row <= i + drawRange; row++) {
            if (row < 0 || row >= n || board[row][j] == '.') return false;
        }
        return true;
    }

    private static void overwritten(int i, int j, int drawRange) {
        for (int col = j - drawRange; col <= j + drawRange; col++) {
            doNotDrawHere[i][col] = false;
        }
        for (int row = i - drawRange; row <= i + drawRange; row++) {
            doNotDrawHere[row][j] = false;
        }
    }

    private static boolean canMakeBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (doNotDrawHere[i][j]) return false;
            }
        }
        return true;
    }

}
