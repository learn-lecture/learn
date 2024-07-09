package com.study.datastructrue.graph.mst.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ArrayKruskal {

    private List<KruskalNode> edges;
    private int[] parent, ranks;
    private int minimumDistance;
    private Set<Integer> path;

    public ArrayKruskal(int size) {
        this.edges = new ArrayList<>();
        this.parent = new int[size + 1];
        this.ranks = new int[size + 1];
        this.minimumDistance = Integer.MAX_VALUE;
        this.path = new LinkedHashSet<>();
        initParent(size + 1);
    }

    public void update() {
        this.minimumDistance = 0;
        Collections.sort(this.edges);
        for (KruskalNode node : this.edges) {
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
            this.ranks[i] = 0; // rank 초기화
        }
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
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