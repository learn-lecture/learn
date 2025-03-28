package com.study.baekjoon.greedy.boj18310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
    static int[] village = new int[100001];
    static int n, result;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        long dist = 0;
        for (int i = 0; i < n; i++) {
            int pos = Integer.parseInt(st.nextToken());
            village[pos]++;
            dist += pos;
        }

        int left = 0, right = n;
        long leftDistance = 0;
        long rightDistance = dist;
        for (int pos = 1; pos <= 100000; pos++) {
            leftDistance += left;
            rightDistance -= right;

            if (village[pos] == 0) continue;
            right -= village[pos];
            left += village[pos];

            long totalDistance = leftDistance + rightDistance;
            if (totalDistance < dist) {
                dist = totalDistance;
                result = pos;
            }
        }
        System.out.println(result);
    }

}
