package com.study.datastructrue.graph.shortestpath;

public class DijkstraClient {

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra(13);
        dijkstra.add(1, 3, 4);      dijkstra.add(1, 4, 7);
        dijkstra.add(1, 8, 12);     dijkstra.add(2, 3, 2);
        dijkstra.add(2, 6, 3);      dijkstra.add(3, 4, 3);
        dijkstra.add(3, 5, 11);     dijkstra.add(4, 7, 2);
        dijkstra.add(4, 8, 6);      dijkstra.add(4, 10, 4);
        dijkstra.add(5, 6, 2);      dijkstra.add(5, 10, 6);
        dijkstra.add(7, 9, 9);      dijkstra.add(7, 10, 8);
        dijkstra.add(7, 13, 7);     dijkstra.add(8, 9, 3);
        dijkstra.add(9, 12, 1);     dijkstra.add(9, 13, 13);
        dijkstra.add(10, 11, 2);    dijkstra.add(11, 13, 5);

        for (int i = 1; i <= 13; i++) {
            System.out.println(
                    dijkstra.getMinDist(1, i)
            );
        }

        dijkstra.printAllDist();
    }

}
