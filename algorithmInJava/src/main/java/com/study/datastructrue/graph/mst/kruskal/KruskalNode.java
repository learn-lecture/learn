package com.study.datastructrue.graph.mst.kruskal;

public class KruskalNode implements Comparable<KruskalNode> {

    int u;
    int v;
    int weight;

    public KruskalNode(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(KruskalNode o) {
        return this.weight - o.weight;
    }

}
