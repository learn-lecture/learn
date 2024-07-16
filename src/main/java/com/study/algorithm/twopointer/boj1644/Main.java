package com.study.algorithm.twopointer.boj1644;

import java.io.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX_NUMBER = 4000000;
    static boolean[] isNotPrime = new boolean[MAX_NUMBER + 1];

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(BR.readLine());
        eratosthenes();
        int cnt = 0;
        for (int right = 2, left = 2, answer = 0; right <= n; right++) {
            if (isNotPrime[right]) continue;
            answer += right;

            while (answer > n) {
                if (!isNotPrime[left]) {
                    answer -= left;
                }
                left++;
            }

            if (answer == n) cnt++;
        }
        System.out.println(cnt);
    }

    private static void eratosthenes() {
        for (int i = 2; i * i <= MAX_NUMBER; i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * i; j <= MAX_NUMBER; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

}
