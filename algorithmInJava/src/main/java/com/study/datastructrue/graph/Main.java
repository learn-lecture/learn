package com.study.datastructrue.graph;

public class Main {

	public static void main(String[] args) {
		AdjacencyMatrix matGraph = new AdjacencyMatrix(5);
		AdjacnecyList listGraph = new AdjacnecyList(5);
		// a -> b, a -> c, a -> d
		// b -> d, d -> e, e -> b
		System.out.println("Directed Adj Matrix Graph");
		System.out.println("  A B C D E");
		matGraph.dirAdd(0, 1, 2);			matGraph.dirAdd(0, 2, 5);
		matGraph.dirAdd(0, 3, 3);			matGraph.dirAdd(1, 3, 6);
		matGraph.dirAdd(3, 4, 4);			matGraph.dirAdd(4, 1, 7);
		matGraph.dirPrint();

		System.out.println();
		System.out.println("Directed Adj List Graph");
		listGraph.dirAdd(0, 1, 2);			listGraph.dirAdd(0, 2, 5);
		listGraph.dirAdd(0, 3, 3);			listGraph.dirAdd(1, 3, 6);
		listGraph.dirAdd(3, 4, 4);			listGraph.dirAdd(4, 1, 7);
		listGraph.dirPrint();

		System.out.println();
		// a - b, a - c, a - d
		// c - d, d - e
		System.out.println("UnDirected Adj Matrix Graph");
		System.out.println("  A B C D E");
		matGraph.unDirAdd(0, 1, 2);		matGraph.unDirAdd(0, 2, 5);
		matGraph.unDirAdd(0, 3, 3);		matGraph.unDirAdd(2, 3, 6);
		matGraph.unDirAdd(3, 4, 4);
		matGraph.unDirPrint();

		System.out.println();
		System.out.println("UnDirected Adj List Graph");
		listGraph.unDirAdd(0, 1, 2);		listGraph.unDirAdd(0, 2, 5);
		listGraph.unDirAdd(0, 3, 3);		listGraph.unDirAdd(2, 3, 6);
		listGraph.unDirAdd(3, 4, 4);
		listGraph.unDirPrint();

	}

}
