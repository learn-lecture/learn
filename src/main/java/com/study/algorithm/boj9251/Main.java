package com.study.algorithm.boj9251;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] str1, str2;
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        str1 = BR.readLine().toCharArray();
        str2 = BR.readLine().toCharArray();

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        BW.write(String.valueOf(dp[str1.length][str2.length]));
        BW.flush();
        BW.close();
        BR.close();
    }

}
