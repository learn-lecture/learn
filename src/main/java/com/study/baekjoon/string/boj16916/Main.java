package com.study.baekjoon.string.boj16916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s, p;
    public static void main(String[] args) throws IOException {
        s = br.readLine();
        p = br.readLine();
        System.out.println(kmpSearch());
    }

    static int[] computeLPS() {
        int[] lps = new int[p.length()];
        int len = 0;
        int i = 1;

        while (i < p.length()) {
            if (p.charAt(i) == p.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    static int kmpSearch() {
        int[] lps = computeLPS();
        int i = 0;
        int j = 0;

        while (i < s.length()) {
            if (p.charAt(j) == s.charAt(i)) {
                i++;
                j++;
            }

            if (j == p.length()) {
                return 1;
            } else if (i < s.length() && p.charAt(j) != s.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return 0;
    }

}
