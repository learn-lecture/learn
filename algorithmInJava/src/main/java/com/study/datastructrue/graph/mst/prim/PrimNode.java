package com.study.datastructrue.graph.mst.prim;

public class PrimNode implements Comparable<PrimNode>{

    int v;
    int weight;

    public PrimNode(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(PrimNode o) {
        return this.weight - o.weight;
    }

}
