package com.study.baekjoon.string.boj14426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<String> prefix = new HashSet<>(10001);
    static int n, m;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<String> str = new ArrayList<>(10001);
        for (int i = 0; i < n; i++) {
            str.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            prefix.add(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : str.get(i).toCharArray()) {
                sb.append(c);
                prefix.remove(sb.toString());
            }
        }

        System.out.println((m - prefix.size()));
    }

}
