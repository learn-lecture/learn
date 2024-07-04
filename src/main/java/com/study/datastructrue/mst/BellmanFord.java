package com.study.datastructrue.mst;

import java.util.ArrayList;
import java.util.Arrays;

class Pair<T, V> {

    T node;
    V cost;

    public Pair(T node, V cost) {
        this.node = node;
        this.cost = cost;
    }

}

public class BellmanFord {

    static long[] dist = new long[6];

    public static void main(String[] args) {
        // 집 1, 학교 2, 박물관 3, 영화관 4, 마트 5
        ArrayList<ArrayList<Pair<Integer, Integer>>> graph = input();
        for (int i = 1; i < graph.size(); i++) {
            dist = bellmanFord(graph, i);
            for (long dis: dist) {
                System.out.printf((dis > 1e9 ? "INF" : dis) + " ");
            }
            System.out.println();
        }

        for (int i = 1; i < graph.size(); i++) {
            for (Pair<Integer, Integer> path : graph.get(i)) {
                if (dist[path.node] > dist[i] + path.cost) {
                    System.out.print("negative cycle exists ........");
                }
            }
        }
    }

    public static ArrayList<ArrayList<Pair<Integer, Integer>>> input() {
        ArrayList<ArrayList<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(new Pair<>(2, -1));
        graph.get(1).add(new Pair<>(5, 4));
        graph.get(2).add(new Pair<>(3, 2));
        graph.get(2).add(new Pair<>(4, 1));
        graph.get(2).add(new Pair<>(5, 3));
        graph.get(3).add(new Pair<>(4, -3)); // 음수 값에 따라 음수 사이클 판별 가능
        graph.get(4).add(new Pair<>(2, 2));
        graph.get(4).add(new Pair<>(5, 5));

        return graph;
    }

    public static long[] bellmanFord(
            ArrayList<ArrayList<Pair<Integer, Integer>>> graph,
            int start
    ) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int node = 0; node < 5; node++) {
            for (int i = 1; i < graph.size(); i++) {
                for (Pair<Integer, Integer> path : graph.get(i)) {
                    if (dist[path.node] > dist[i] + path.cost) {
                        dist[path.node] = dist[i] + path.cost;
                    }
                }
            }
        }

        return dist;
    }

}
