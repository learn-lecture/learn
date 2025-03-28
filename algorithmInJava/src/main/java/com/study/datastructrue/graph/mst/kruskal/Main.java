package com.study.datastructrue.graph.mst.kruskal;

public class Main {

    public static void main(String[] args) {
        PriorityQueueKruskal pKruskal = new PriorityQueueKruskal(5);
        pKruskal.add(1, 2, 6);      pKruskal.add(1, 3, 3);
        pKruskal.add(1, 4, 1);      pKruskal.add(2, 5, 4);
        pKruskal.add(3, 4, 2);      pKruskal.add(3, 5, 5);
        pKruskal.add(4, 5, 7);      pKruskal.update();

        System.out.println(pKruskal.getMinimumDistance());
        pKruskal.printTrace();

        ArrayKruskal aKruskal = new ArrayKruskal(5);
        aKruskal.add(1, 2, 6);      aKruskal.add(1, 3, 3);
        aKruskal.add(1, 4, 1);      aKruskal.add(2, 5, 4);
        aKruskal.add(3, 4, 2);      aKruskal.add(3, 5, 5);
        aKruskal.add(4, 5, 7);      aKruskal.update();

        System.out.println(aKruskal.getMinimumDistance());
        aKruskal.printTrace();
    }

}
