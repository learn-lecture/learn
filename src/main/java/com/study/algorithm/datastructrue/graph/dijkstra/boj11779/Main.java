package com.study.algorithm.datastructrue.graph.dijkstra.boj11779;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {

    int v, w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }

}

public class Main {

    static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder SB = new StringBuilder();
    static List<Edge>[] GRAPH = new List[1001];
    static int DIST[] = new int[1001];
    static int PATH[] = new int[1001];
    static StringTokenizer ST;
    static int N, M, S, E;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(BR.readLine());
        M = Integer.parseInt(BR.readLine());
        for (int i = 1; i <= N; i++) {
            GRAPH[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            ST = new StringTokenizer(BR.readLine());
            int a = Integer.parseInt(ST.nextToken());
            int b = Integer.parseInt(ST.nextToken());
            int w = Integer.parseInt(ST.nextToken());
            GRAPH[a].add(new Edge(b, w));
        }

        ST = new StringTokenizer(BR.readLine());
        S = Integer.parseInt(ST.nextToken());
        E = Integer.parseInt(ST.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(DIST, (int)1e9);
        pq.offer(new Edge(S, 0));
        DIST[S] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (DIST[edge.v] < edge.w) continue;
            for (Edge next : GRAPH[edge.v]) {
                if (DIST[edge.v] + next.w >= DIST[next.v]) continue;
                DIST[next.v] = DIST[edge.v] + next.w;
                pq.offer(next);
                PATH[next.v] = edge.v;
            }
        }

        SB.append(DIST[E]).append('\n');
        trace(E, 0);
        BW.write(SB.toString());
        BW.flush();
    }

    static void trace(int v, int depth) {
        if (v == 0) {
            SB.append(depth).append('\n');
            return;
        }
        trace(PATH[v], depth + 1);
        SB.append(v).append(' ');
    }
}
