package com.study.baekjoon.graph.mst.boj1647;
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{

	int u;
	int v;
	int w;

	Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return this.w - o.w;
	}

}

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static PriorityQueue<Edge> edges = new PriorityQueue<>();
	static int PARENT[] = new int[100001];
	static int RANK[] = new int[100001];
	static int N, M, DISTANCE, MAX_DIST;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			PARENT[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(BR.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges.offer(new Edge(u, v, w));
		}

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			int parentA = find(edge.u);
			int parentB = find(edge.v);
			if (parentA != parentB) {
				union(parentA, parentB);
				DISTANCE += edge.w;
				MAX_DIST = Math.max(MAX_DIST, edge.w);
			}
		}

		BW.write(String.valueOf(DISTANCE - MAX_DIST));
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