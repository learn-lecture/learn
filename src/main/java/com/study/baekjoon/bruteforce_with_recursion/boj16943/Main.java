package com.study.baekjoon.bruteforce_with_recursion.boj16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] visited = new boolean[10];
    static String a, b;
    static int len;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken();
        b = st.nextToken();
        len = a.length();
        System.out.println(rec(0, 0));
    }

    private static int rec(int k, int c) {
        if (k == len && len == String.valueOf(c).length() && c < Integer.parseInt(b)) {
            return c;
        }

        int maxC = -1;
        for (int i = 0; i < a.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            maxC = Math.max(maxC, rec(k + 1, c * 10 + (a.charAt(i) - '0')));
            visited[i] = false;
        }
        return maxC;
    }

}
