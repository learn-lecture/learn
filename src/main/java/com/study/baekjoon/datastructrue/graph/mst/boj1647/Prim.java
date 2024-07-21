package com.study.baekjoon.datastructrue.graph.mst.boj1647;

import java.io.*;
import java.util.*;

class PrimEdge implements Comparable<PrimEdge> {

	int v;
	int w;

	PrimEdge(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(PrimEdge o) {
		return this.w - o.w;
	}

}

public class Prim {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static List<PrimEdge>[] edges = new List[100001];
	static boolean[] visited = new boolean[100001];
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		int u, v, w;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(BR.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edges[u].add(new PrimEdge(v, w));
			edges[v].add(new PrimEdge(u, w));
		}

		PriorityQueue<PrimEdge> pq = new PriorityQueue();
		pq.offer(new PrimEdge(1, 0));
		int dist = 0;
		int maxDist = 0;

		while (!pq.isEmpty()) {
			PrimEdge primEdge = pq.poll();

			if (visited[primEdge.v]) continue;
			visited[primEdge.v] = true;
			dist += primEdge.w;
			maxDist = Math.max(maxDist, primEdge.w);

			for (PrimEdge next: edges[primEdge.v]) {
				if (visited[next.v]) continue;
				pq.offer(next);
			}
		}

		BW.write(String.valueOf(dist-maxDist));
		BW.flush();
		BW.close();
		BR.close();
	}

}
