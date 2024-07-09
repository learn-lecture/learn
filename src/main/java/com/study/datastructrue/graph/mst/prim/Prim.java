package com.study.datastructrue.graph.mst.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    private List<PrimNode>[] graph;
    private List<Integer> path;
    private boolean[] visited;
    private int minimumDistance;

    public Prim(int size) {
        this.graph = new List[size + 1];
        this.path = new ArrayList<>();
        this.visited = new boolean[size + 1];
        this.minimumDistance = Integer.MAX_VALUE;
        initGraph(size);
    }

    private void initGraph(int size) {
        for (int i = 0; i <= size; i++) {
            this.graph[i] = new ArrayList<>();
        }
    }

    public void add(int u, int v, int w) {
        this.graph[u].add(new PrimNode(v, w));
        this.graph[v].add(new PrimNode(u, w));
    }

    public void update(int start) {
        Arrays.fill(visited, false);
        path.clear();
        this.minimumDistance = 0;

        PriorityQueue<PrimNode> pq = new PriorityQueue<>();
        pq.offer(new PrimNode(start, 0));

        while(!pq.isEmpty()) {
            PrimNode node = pq.poll();

            if (visited[node.v]) continue;
            visited[node.v] = true;
            this.minimumDistance += node.weight;
            path.add(node.v);

            for (PrimNode next: graph[node.v]) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }
    }

    public int getMinimumDistance() {
        return this.minimumDistance;
    }

    public void printTrace() {
        for (int v: path) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

}
