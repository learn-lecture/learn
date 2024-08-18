package com.study.baekjoon.sorting.boj5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] phoneBook;
    static int t, n;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            phoneBook = new String[n];

            for (int i = 0; i < n; i++) {
                String tel = br.readLine();
                phoneBook[i] = tel;
            }
            Arrays.sort(phoneBook, 0, n, Comparator.comparingInt(String::length));
            sb.append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static String solve() {
        Set<String> prefix = new HashSet<>();
        for (String tel : phoneBook) {
            for (int len = 1; len < tel.length(); len++) {
                if (prefix.contains(tel.substring(0, len))) {
                    return "NO";
                }
            }
            prefix.add(tel);
        }
        return "YES";
    }
}
