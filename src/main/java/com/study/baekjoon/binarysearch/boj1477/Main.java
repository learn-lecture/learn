package com.study.baekjoon.binarysearch.boj1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] rest = new int[51];
    static int n, m, l;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }
        rest[n] = l;
        Arrays.sort(rest, 0, n);

        int s = 1, e = l;
        while (s <= e) {
            int mid = (s + e) / 2;
            int cnt = (rest[0] - 1) / mid;
            for(int i = 1; i <= n; i++) {
                cnt += (rest[i] - rest[i - 1] - 1) / mid;
            }
            if (cnt > m) s = mid + 1;
            else e = mid - 1;
        }

        System.out.println(s);
    }

}