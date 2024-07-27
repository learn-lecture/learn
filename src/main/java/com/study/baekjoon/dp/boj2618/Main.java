package com.study.baekjoon.dp.boj2618;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder SB = new StringBuilder();
    static int[][] DP = new int[1001][1001];
    static List<Pair> EVENT1, EVENT2;
    static StringTokenizer ST;
    static int N, M;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int getDist1(int police, int event) {
        return Math.abs(EVENT1.get(police).x - EVENT1.get(event).x)
                + Math.abs(EVENT1.get(police).y - EVENT1.get(event).y);
    }

    static int getDist2(int police, int event) {
        return Math.abs(EVENT2.get(police).x - EVENT2.get(event).x)
                + Math.abs(EVENT2.get(police).y - EVENT2.get(event).y);
    }

    static int rec(int p1, int p2) {
        if (p1 == M || p2 == M) return 0;
        if (DP[p1][p2] > -1) return DP[p1][p2];

        int event = Math.max(p1, p2) + 1;

        int result1 = rec(event, p2) + getDist1(p1, event);
        int result2 = rec(p1, event) + getDist2(p2, event);

        return DP[p1][p2] = Math.min(result1, result2);
    }

    static void trace(int p1, int p2) {
        if (p1 == M || p2 == M) return ;

        int event = Math.max(p1, p2) + 1;

        int result1 = rec(event, p2) + getDist1(p1, event);
        int result2 = rec(p1, event) + getDist2(p2, event);

        if (result1 > result2) {
            SB.append('2').append('\n');
            trace(p1, event);
        } else {
            SB.append('1').append('\n');
            trace(event, p2);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(BR.readLine());
        M = Integer.parseInt(BR.readLine());

        for (int[] dp: DP) Arrays.fill(dp, -1);
        EVENT1 = new ArrayList<>((M + 1));  EVENT1.add(new Pair(1, 1));
        EVENT2 = new ArrayList<>((M + 1));  EVENT2.add(new Pair(N, N));

        for (int i = 0; i < M; i++) {
            ST = new StringTokenizer(BR.readLine());
            Pair event = new Pair(Integer.parseInt(ST.nextToken()), Integer.parseInt(ST.nextToken()));
            EVENT1.add(event);
            EVENT2.add(event);
        }

        SB.append(rec(0, 0)).append('\n');
        trace(0, 0);

        BW.write(SB.toString());
        BW.flush();
    }

}
