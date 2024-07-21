package com.study.baekjoon.datastructrue.graph.bfs.boj2638;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Pair> Q = new LinkedList<>();
	static int[][] GRAPH = new int[101][101];
	static int[] DX = {1, 0, -1, 0}, DY = {0, 1, 0, -1};
	static StringTokenizer ST;
	static int N, M;

	public static void main(String[] args) throws IOException {
		ST = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(ST.nextToken());
		M = Integer.parseInt(ST.nextToken());

		for (int i = 0; i < N; i++) {
			ST = new StringTokenizer(BR.readLine());
			for (int j = 0; j < M; j++) {
				GRAPH[i][j] = Integer.parseInt(ST.nextToken());
			}
		}

		int totalNegativeCount = bfs(0, 0);
		int expect = N * M;
		int time = 0;
		while (totalNegativeCount != expect) {
			ArrayList<Pair> externalCheese = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (GRAPH[i][j] == 1) {
						int external = 0;
						for (int k = 0; k < 4; k++) {
							if (GRAPH[i + DX[k]][j + DY[k]] == -1) {
								external++;
							}
						}
						if (external >= 2) {
							externalCheese.add(new Pair(i, j));
						}
					}
				}
			}
			for (Pair cheesePos: externalCheese) {
				GRAPH[cheesePos.x][cheesePos.y] = 0;
				totalNegativeCount += bfs(cheesePos.x, cheesePos.y);
			}
			time++;
		}
		System.out.println(time);
	}

	static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int bfs(int x, int y) {
		int negativeCount = 0;
		Q.offer(new Pair(x, y));
		GRAPH[x][y] = -1;

		while (!Q.isEmpty()) {
			Pair pos = Q.poll();
			negativeCount++;

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + DX[i];
				int ny = pos.y + DY[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (GRAPH[nx][ny] != 0) continue;
				GRAPH[nx][ny] = -1;
				Q.offer(new Pair(nx, ny));
			}
		}

		return negativeCount;
	}

}
