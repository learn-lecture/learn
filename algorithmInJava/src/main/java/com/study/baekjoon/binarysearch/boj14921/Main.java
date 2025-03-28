package com.study.baekjoon.binarysearch.boj14921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[100005];
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = n - 1;
        int diff = (int) 1e9;
        while (s < e) {
            int val = arr[s] + arr[e];
            if (Math.abs(val) < Math.abs(diff)) diff = val;
            if (val < 0) s++;
            else e--;
        }

        System.out.println(diff);
    }

}
