package com.study.datastructrue.graph.mst.prim;

import com.study.datastructrue.graph.mst.kruskal.PriorityQueueKruskal;

public class Main {

    public static void main(String[] args) {
        Prim prim = new Prim(5);
        prim.add(1, 2, 6);      prim.add(1, 3, 3);
        prim.add(1, 4, 1);      prim.add(2, 5, 4);
        prim.add(3, 4, 2);      prim.add(3, 5, 5);
        prim.add(4, 5, 7);      prim.update(1);

        System.out.println(prim.getMinimumDistance());
        prim.printTrace();
    }

}
