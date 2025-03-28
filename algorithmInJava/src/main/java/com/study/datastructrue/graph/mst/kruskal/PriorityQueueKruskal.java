package com.study.datastructrue.graph.mst.kruskal;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PriorityQueueKruskal {

    private PriorityQueue<KruskalNode> edges;
    private Set<Integer> path;
    private int[] parent, ranks;
    private int minimumDistance;

    public PriorityQueueKruskal(int size) {
        this.edges = new PriorityQueue<>();
        this.parent = new int[size + 1];
        this.ranks = new int[size + 1];
        this.minimumDistance = Integer.MAX_VALUE;
        this.path = new LinkedHashSet<>();
        initParent(size + 1);
    }

    public void update() {
        this.minimumDistance = 0;
        PriorityQueue<KruskalNode> temp = new PriorityQueue<>(this.edges);
        while (!temp.isEmpty()) {
            KruskalNode node = temp.poll();
            int parentA = find(node.u);
            int parentB = find(node.v);
            if (parentA != parentB) {
                union(parentA, parentB);
                this.minimumDistance += node.weight;
                this.path.add(node.u);
                this.path.add(node.v);
            }
        }
    }

    public int getMinimumDistance() {
        return minimumDistance;
    }

    public void printTrace() {
        System.out.println(path.toString());
    }

    public void add(int u, int v, int w) {
        this.edges.add(new KruskalNode(u, v, w));
    }

    private void initParent(int size) {
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
        }
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        if (x == y) {
            return;
        }

        if (ranks[x] < ranks[y]) {
            int temp = ranks[x];
            ranks[x] = ranks[y];
            ranks[y] = temp;
        }

        parent[y] = x;

        if(ranks[x] == ranks[y]) {
            ranks[x] = ranks[y] + 1;
        }
    }

}
