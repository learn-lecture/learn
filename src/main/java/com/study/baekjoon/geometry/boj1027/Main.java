package com.study.baekjoon.geometry.boj1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* acmicpc 1027, 고층 건물
* 25.01.07 PM 10:37~11:17
* */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr = new int[55];
    static StringTokenizer st;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve());
    }

    static int solve() {
        int maxViewCount = 0;
        for (int point = 0; point < n; point++) {
            int viewCount = 0;
            for (int basePoint = 0; basePoint < n; basePoint++) {
                if (point == basePoint) continue;
                if (canView(Math.min(point, basePoint), Math.max(point, basePoint))) {
                    viewCount++;
                }
            }
            maxViewCount = Math.max(maxViewCount, viewCount);
        }
        return maxViewCount;
    }

    static boolean canView(int start, int destination) {
        double slope = (double)(arr[destination] - arr[start]) / (destination - start);
        for(int i = start + 1; i < destination; i++) {
            double height = arr[start] + slope * (i - start);
            if(arr[i] >= height) {
                return false;
            }
        }
        return true;
    }
}
