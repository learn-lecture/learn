package com.study.algorithm.geometry.boj2166;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(BR.readLine());
        int arr[][] = new int[10001][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(BR.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
        }
        arr[n] = arr[0];

        long area = 0;
        for (int i = 0; i < n; i++) {
            area += (long) arr[i][0] * arr[i+1][1];
            area -= (long) arr[i][1] * arr[i+1][0];
        }
        System.out.printf("%.1f", Math.abs(area) * 0.5);
        BR.close();
    }

}
