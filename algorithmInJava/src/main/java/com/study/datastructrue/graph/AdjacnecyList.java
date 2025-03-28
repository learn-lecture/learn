package com.study.datastructrue.graph;

import java.util.ArrayList;
import java.util.Objects;

public class AdjacnecyList {

	private ArrayList<ArrayList<Object>> directedGraph = new ArrayList<>();
	private ArrayList<ArrayList<Object>> unDirectedGraph = new ArrayList<>();

	public AdjacnecyList(int size) {
		for (int i = 0; i <= size; i++) {
			directedGraph.add(new ArrayList<>());
			unDirectedGraph.add(new ArrayList<>());
		}
	}

	public void dirAdd(int u, int v) {
		directedGraph.get(u).add(v);
	}

	public void dirAdd(int u, int v, int w) {
		directedGraph.get(u).add(new Edge(v, w));
	}

	public void dirPrint() {
		for (int i = 0; i < directedGraph.size(); i++) {
			System.out.print((char)(i + 'A') + ": ");
			for (Object obj : directedGraph.get(i)) {
				if (obj instanceof Integer) {
					System.out.print((char)((int)obj + 'A'));
				} else if (obj instanceof Edge) {
					Edge edge = (Edge) obj;
					System.out.print("{" + (char)(edge.vertex + 'A') + ", " + edge.weight + "}");
				}
				System.out.print(" -> ");
			}
			System.out.println();
		}
	}

	public void unDirAdd(int u, int v) {
		unDirectedGraph.get(u).add(v);
		unDirectedGraph.get(v).add(u);
	}

	public void unDirAdd(int u, int v, int w) {
		unDirectedGraph.get(u).add(new Edge(v, w));
		unDirectedGraph.get(v).add(new Edge(u, w));
	}

	public void unDirPrint() {
		for (int i = 0; i < unDirectedGraph.size(); i++) {
			System.out.print((char)(i + 'A') + ": ");
			for (Object obj : unDirectedGraph.get(i)) {
				if (obj instanceof Integer) {
					System.out.print((char)((int)obj + 'A'));
				} else if (obj instanceof Edge) {
					Edge edge = (Edge) obj;
					System.out.print("{" + (char)(edge.vertex + 'A') + ", " + edge.weight + "}");
				}
				System.out.print(" -> ");
			}
			System.out.println();
		}
	}
	private class Edge {

		int vertex;
		int weight;

		Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

	}
}
