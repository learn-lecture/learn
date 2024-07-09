package com.study.algorithm.boj1197;

import java.io.*;
import java.util.*;

class Edge {

	int v;
	int w;

	Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}

}

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int dist[] = new int[10001];
	static int N, M;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(BR.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Edge(v, w));
			graph.get(v).add(new Edge(u, w));
		}

		bellmanFord();
		for (int i = 0; i <= N; i++) {
			System.out.print(dist[i] + " ");
		}
		System.out.println();
		int minDist = Integer.MAX_VALUE;
		for (int i = 2; i <= N; i++) {
			Math.min(minDist, dist[i]);
		}
		BW.write(String.valueOf(minDist));
		BW.flush();
		BW.close();
		BR.close();
	}

	private static void bellmanFord() {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		for (int i = 1; i < N; i++) {
			for (int v = 1; v <= N; v++) {
				if (dist[v] == Integer.MAX_VALUE) continue;
				for (Edge e: graph.get(v)) {
					if (dist[e.v] > dist[v] + e.w) {
						dist[e.v] = dist[v] + e.w;
					}
				}
			}
		}
	}


}
