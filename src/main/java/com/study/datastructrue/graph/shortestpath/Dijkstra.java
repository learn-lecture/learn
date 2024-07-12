package com.study.datastructrue.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class DijkstraNode implements Comparable<DijkstraNode> {

    int v, w;

    public DijkstraNode(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(DijkstraNode o) {
        return this.w - o.w;
    }

}

public class Dijkstra {

    private List<List<DijkstraNode>> graph;
    private int[] dist;

    public Dijkstra(int size) {
        this.graph = new ArrayList<>();
        this.dist = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            graph.add(new ArrayList<>());
        }
        Arrays.fill(this.dist, Integer.MAX_VALUE);
    }

    public void add(int u, int v, int w) {
        this.graph.get(u).add(new DijkstraNode(v, w));
        this.graph.get(v).add(new DijkstraNode(u, w));
    }

    private void update(int start) {
        if (dist[start] == 0) {
            System.out.println("test: using cache");
            return;
        }
        System.out.println("test: update");
        Arrays.fill(this.dist, Integer.MAX_VALUE);

        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>();
        pq.offer(new DijkstraNode(start, 0));
        this.dist[start] = 0;

        while (!pq.isEmpty()) {
            DijkstraNode cur = pq.poll();
            if (dist[cur.v] < cur.w) continue;
            for (DijkstraNode next : graph.get(cur.v)) {
                if (dist[cur.v] + next.w >= dist[next.v]) continue;
                dist[next.v] = dist[cur.v] + next.w;
                pq.offer(next);
            }
        }
    }

    public int getMinDist(int u, int v) {
        update(u);
        return dist[v];
    }

    public void printAllDist() {
        for (int i : dist) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
