package com.study.baekjoon.binarysearch.boj1072;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long x, y;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int s = 1;
        int e = (int) 1e9;
        long baseLineWinRate = (y * 100) / x;
        int val = -1;
        while (s <= e) {
            int mid = (s + e) / 2;
            long total = x + mid;
            long win = y + mid;
            long winningRate = (win * 100) / total;
            if (winningRate > baseLineWinRate) {
                val = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(val);
    }

}
