package com.study.baekjoon.greedy.boj2437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);
        int sum = 1;
        for (int i = 0; i < n; i++) {
            if (sum < arr[i]) break;
            sum += arr[i];
        }

        System.out.println(sum);
    }

}
