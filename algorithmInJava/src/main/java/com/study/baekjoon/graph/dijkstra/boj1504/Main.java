package com.study.baekjoon.graph.dijkstra.boj1504;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {

    int v, w;
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
    static List<List<Edge>> graph = new ArrayList<>();
    static int[] dist;
    static int N, M, S, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(BR.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(BR.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        st = new StringTokenizer(BR.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dijkstra(1);
        int dis1 = dist[S];
        int dis2 = dist[E];

        dijkstra(S);    dis1 += dist[E];    dis2 += dist[N];
        dijkstra(E);    dis1 += dist[N];    dis2 += dist[S];

        int dis = Math.min(dis1, dis2);
        if (dis >= 200000001) {
            BW.write(String.valueOf(-1));
        } else {
            BW.write(String.valueOf(dis));
        }
        BW.flush();
        BW.close();
        BR.close();
    }

    static void dijkstra (int u) {
        Arrays.fill(dist, 200000001);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(u, 0));
        dist[u] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (dist[edge.v] < edge.w) continue;
            for (Edge next : graph.get(edge.v)) {
                if (dist[edge.v] + next.w >= dist[next.v]) continue;
                dist[next.v] = dist[edge.v] + next.w;
                pq.offer(next);
            }
        }
    }

}
