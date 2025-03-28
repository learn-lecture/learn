package com.study.baekjoon.graph.bellmanford.boj1865;
import java.io.*;
import java.util.*;

class Pair {
    int e;
    int t;

    Pair(int e, int t) {
        this.e = e;
        this.t = t;
    }

}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<ArrayList<Pair>> STATIONS;
    static int N, M, W;
    static int INF = (int)1e8;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(st.nextToken());
        while (tc-- > 0) {
            input();
            //inputDebug();
            sb.append(bellmanFord());
        }
        //inputDebug();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bellmanFord() {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int loop = 0; loop <= N; loop++) {
            for (int i = 1; i <= N; i++) {
                for (Pair path : STATIONS.get(i)) {
                    if (dist[path.e] > dist[i] + path.t) {
                        if (loop == N) {
                            return "YES\n";
                        }
                        dist[path.e] = dist[i] + path.t;
                    }
                }
            }
        }

        return "NO\n";
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        STATIONS = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            STATIONS.add(new ArrayList<>());
        }

        int cnt = M + W;
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (i < M) {
                STATIONS.get(s).add(new Pair(e, t));
                STATIONS.get(e).add(new Pair(s, t));
            } else {
                STATIONS.get(s).add(new Pair(e, -t));
            }
        }
    }
/*
    private static void inputDebug() {
            for (int i = 0; i < STATIONS.size(); i++) {
                for (Pair p : STATIONS.get(i)) {
                    System.out.printf("%d -> %d : %d\n", i, p.e, p.t);
                }
            }
    }*/

}
