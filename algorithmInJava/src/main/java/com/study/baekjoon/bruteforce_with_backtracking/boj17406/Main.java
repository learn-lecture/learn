package com.study.baekjoon.bruteforce_with_backtracking.boj17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Rotation {
        int r, c, s;
        public Rotation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;
    static int[][] arr = new int[51][51];
    static int[][] tempArr = new int[51][51];
    static boolean[] visited = new boolean[6];
    static Rotation[] rotationOrder = new Rotation[6];
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Rotation[] rotations = input();
        backtrack(rotations, 0);
        System.out.println(minValue);
    }

    private static Rotation[] input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Rotation[] rotations = new Rotation[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotations[i] = new Rotation(r, c, s);
        }
        return rotations;
    }

    private static void backtrack(Rotation[] rotations, int depth) {
        if (depth == k) {
            for (int i = 1; i <= n; i++) tempArr[i] = Arrays.copyOf(arr[i], arr[i].length);
            for (int i = 0; i < k; i++) rotateArray(i);
            calculateArrayValue();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                rotationOrder[depth] = rotations[i];
                backtrack(rotations, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void rotateArray(int idx) {
        int r = rotationOrder[idx].r;
        int c = rotationOrder[idx].c;
        int s = rotationOrder[idx].s;

        for (int layer = 1; layer <= s; layer++) {
            int sr = r - layer, sc = c - layer;
            int er = r + layer, ec = c + layer;

            int temp = tempArr[sr][sc];
            for (int i = sr; i < er; i++) tempArr[i][sc] = tempArr[i+1][sc];
            for (int j = sc; j < ec; j++) tempArr[er][j] = tempArr[er][j+1];
            for (int i = er; i > sr; i--) tempArr[i][ec] = tempArr[i-1][ec];
            for (int j = ec; j > sc; j--) tempArr[sr][j] = tempArr[sr][j-1];
            tempArr[sr][sc+1] = temp;
        }
    }

    private static void calculateArrayValue() {
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int rowSum = 0;
            for (int j = 1; j <= m; j++) {
                rowSum += tempArr[i][j];
            }
            minSum = Math.min(minSum, rowSum);
        }

        minValue = Math.min(minValue, minSum);
    }

}

/*
* PM 10:07 ~ 10:54
* 문제 정리
* 배열의 최솟값을 구한다.
* 배열의 최솟값은 각 행의 모든 원소의 합 중 가장 작은 값이다.
* 배열이 주어지면 k번의 회전을 한다.
* 각 회전은 r, c, s 값으로 회전한다.
* (r-s, c-s) 부터 (r+s, c+s) 까지 시계 방향으로 회전한다.
* 회전 연산이 두 개 이상이면, 연산을 수행한 횟수에 따라 최종 배열이 달라진다.
*
* 핵심 포인트
* 다른 연산이 존재하므로 백트래킹
* 배열 회전의 최종 결과로 최종 배열을 획득 할 수 있으므로 가지치기 필요 없음.
*
* 핵심 로직
* 배열 시작 지점부터 종료 지점까지의 시계 방향 회전 로직
* 반시계 방향 회전 로직
* 최종 배열에서 최솟 값을 찾는 로직
* */