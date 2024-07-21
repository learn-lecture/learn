package com.study.baekjoon.datastructrue.graph.mst.boj1197;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{

    int v;
    int w;

    Edge(int v, int w) {
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
    static List<Edge>[] edges = new List[10001];
    static boolean[] visited = new boolean[10001];
    static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(BR.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int distance = 0;

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(BR.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new Edge(v, w));
            edges[v].add(new Edge(u, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visited[edge.v]) continue;
            visited[edge.v] = true;
            distance += edge.w;

            for (Edge next: edges[edge.v]) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }

        BW.write(String.valueOf(distance));
        BW.flush();
        BW.close();
        BR.close();
    }

}
