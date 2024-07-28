package com.study.baekjoon.dp.boj14003;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder SB = new StringBuilder();
    static List<Integer> SEQ = new ArrayList<>();
    static int[] NUMS = new int[1000001];
    static int[] DP = new int[1000001];
    static StringTokenizer ST;
    static int N;

    private static int lowerBound(int s, int e, int key) {
        int val = e, mid;
        while (s < e) {
            mid = (s + e) / 2;
            if (SEQ.get(mid) >= key) {
                val = mid;
                e = mid;
            } else {
                s = mid + 1;
            }
        }
        return val;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(BR.readLine());
        ST = new StringTokenizer(BR.readLine());

        SEQ.add(Integer.valueOf(ST.nextToken()));
        NUMS[0] = SEQ.get(0);

        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(ST.nextToken());
            NUMS[i] = num;
            int index = lowerBound(0, SEQ.size(), num);

            if (index == SEQ.size()) {
                SEQ.add(num);
            } else {
                SEQ.set(index, num);
            }

            DP[i] = index;
        }

        SB.append(SEQ.size()).append('\n');
        trace(SEQ.size() - 1, N - 1, Integer.MAX_VALUE);
        BW.write(SB.toString());
        BW.flush();
    }

    private static void trace(int len, int idx, int prev) {
        if (len < 0) return ;
        int now = NUMS[idx];
        if (DP[idx] == len && now < prev) {
            trace(len - 1, idx - 1, now);
            SB.append(now).append(" ");
        } else {
            trace(len, idx - 1, prev);
        }
    }

}