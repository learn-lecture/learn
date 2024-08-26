package com.study.goorm.lv2.pickupcoins;

import java.io.*;
import java.util.*;

class Main {

    static int[] coin = new int[(int) 1e5 + 1];
    static long[] prefixSum = new long[(int) 1e5 + 1];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        long maxSum = 0;
        for (int i = 1; i <= n; i++) {
            if (prefixSum[i - 1] < 0) prefixSum[i] = coin[i];
            else prefixSum[i] = prefixSum[i - 1] + coin[i];
            maxSum = Math.max(maxSum, prefixSum[i]);
        }
        System.out.print(maxSum);
    }

}