package com.study.baekjoon.datastructrue.graph.floydwarshall.boj14938;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] graph = new int[101][101];
	static int[] items = new int[101];
	static StringTokenizer st;
	static int n, m, r;

	static class Pair {
		int x, l;

		Pair(int x, int l) {
			this.x = x;
			this.l = l;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i <= 100; i++) {
			Arrays.fill(graph[i], (int)1e9);
			graph[i][i] = 0;
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a][b] = graph[b][a] = w;
		}

		for (int diff = 1; diff <= n; diff++) {
			for (int from = 1; from <= n; from++) {
				for (int to = 1; to <= n; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][diff] + graph[diff][to]);
				}
			}
		}

		int maxItem = 0;
		for (int from = 1; from <= n; from++) {
			int getItemCount = 0;
			for (int to = 1; to <= n; to++) {
				if (graph[from][to] > m) continue;
				getItemCount += items[to];
			}
			maxItem = Math.max(getItemCount, maxItem);
		}

		System.out.println(maxItem);
	}

}
