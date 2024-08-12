package com.study.baekjoon.binarysearch.boj2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr = new int[1001];
    static int[] brr = new int[1001];
    static List<Integer> arrs = new ArrayList<>();
    static List<Integer> brrs = new ArrayList<>();

    static int n;
    static int m;
    static int t;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            brr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                arrs.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += brr[j];
                brrs.add(sum);
            }
        }

        brrs.sort(Integer::compareTo);

        long can = 0;
        for (int x: arrs) {
            can += upperBound((t - x)) - lowerBound((t - x));
        }
        System.out.println(can);
    }

    static int lowerBound(int key) {
        int s = 0; int e = brrs.size() - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (brrs.get(mid) >= key) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    static int upperBound(int key) {
        int s = 0; int e = brrs.size() - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (brrs.get(mid) > key) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }
}
