package com.study.baekjoon.binarysearch.boj2295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[1001];
    static int n;
    static Set<Integer> s = new HashSet<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.add(arr[i] + arr[j]);
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                if (s.contains(arr[i] - arr[j])) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }

}
