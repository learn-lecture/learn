package com.study.algorithm.datastructrue.graph.mst.boj1197;

import java.io.*;
import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge>{

	int u;
	int v;
	int w;

	KruskalEdge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(KruskalEdge o) {
		return this.w - o.w;
	}

}

public class Kruskal {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static PriorityQueue<KruskalEdge> kruskalEdges = new PriorityQueue<>();
	static int PARENT[] = new int[10001];
	static int RANK[] = new int[10001];
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int distance = 0;

		for (int i = 0; i <= N; i++) {
			PARENT[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(BR.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			kruskalEdges.offer(new KruskalEdge(u, v, w));
		}

		while (!kruskalEdges.isEmpty()) {
			KruskalEdge kruskalEdge = kruskalEdges.poll();
			int parentA = find(kruskalEdge.u);
			int parentB = find(kruskalEdge.v);
			if (parentA != parentB) {
				union(parentA, parentB);
				distance += kruskalEdge.w;
			}
		}

		BW.write(String.valueOf(distance));
		BW.flush();
		BW.close();
		BR.close();
	}

	private static int find(int x) {
		if (x == PARENT[x]) {
			return x;
		}
		return PARENT[x] = find(PARENT[x]);
	}

	private static void union(int u, int v) {
		if (u == v) return ;

		if (RANK[u] < RANK[v]) {
			int temp = RANK[u];
			RANK[u] = RANK[v];
			RANK[v] = temp;
		}

		PARENT[v] = u;

		if(RANK[u] == RANK[v]) {
			RANK[u] = RANK[v] + 1;
		}
	}

}
