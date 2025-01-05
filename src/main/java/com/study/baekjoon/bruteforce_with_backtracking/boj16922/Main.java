package com.study.baekjoon.bruteforce_with_backtracking.boj16922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, cnt;
    static int[] nums = {1, 5, 10, 50};
    static boolean[] visited = new boolean[1001];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        rec(0, 0, 0);
        System.out.println(cnt);
    }

    private static void rec(int k, int idx, int sum) {
        if (k == n) {
            if (visited[sum]) return;
            visited[sum] = true;
            cnt++;
        } else {
            for (int i = idx; i < 4; i++) {
                rec(k + 1, i, sum + nums[i]);
            }
        }
    }

}
