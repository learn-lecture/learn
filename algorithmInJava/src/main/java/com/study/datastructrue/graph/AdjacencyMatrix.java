package com.study.datastructrue.graph;

public class AdjacencyMatrix {
	private int[][] directedGraph;
	private int[][] unDirectedGraph;

	public AdjacencyMatrix(int size) {
		this.directedGraph = new int[size][size];
		this.unDirectedGraph = new int[size][size];
	}

	public void dirAdd(int u, int v) {
		directedGraph[u][v] = 1;
	}

	public void dirAdd(int u, int v, int w) {
		directedGraph[u][v] = w;
	}

	public void dirPrint() {
		for (int i = 0; i < directedGraph.length; i++) {
			System.out.print((char)(i + 'A'));
			for (int j = 0; j < directedGraph[0].length; j++) {
				System.out.printf("%2d", directedGraph[i][j]);
			}
			System.out.println();
		}
	}

	public void unDirAdd(int u, int v) {
		unDirectedGraph[u][v] = unDirectedGraph[v][u] = 1;
	}

	public void unDirAdd(int u, int v, int w) {
		unDirectedGraph[u][v] = unDirectedGraph[v][u] = w;
	}

	public void unDirPrint() {
		for (int i = 0; i < unDirectedGraph.length; i++) {
			System.out.print((char)(i + 'A'));
			for (int j = 0; j < unDirectedGraph[0].length; j++) {
				System.out.printf("%2d", unDirectedGraph[i][j]);
			}
			System.out.println();
		}
	}

}
