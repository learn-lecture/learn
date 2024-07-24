package com.study.baekjoon.dp.boj1520;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static int[][] GRAPH = new int[501][501];
	static int[][] DP = new int[501][501];
	static int[] DX = {0, 1, 0, -1}, DY = {1, 0, -1, 0};
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

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		DP[0][0] = 1;
		pq.offer(new Pair(0, 0));

		while (!pq.isEmpty()) {
			Pair poll = pq.poll();
			int sum = 0;
			if (poll.x != 0 && poll.y != 0 && DP[poll.x][poll.y] > 0) continue;
			for (int i = 0; i < 4; i++) {
				int nx = poll.x + DX[i];
				int ny = poll.y + DY[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (GRAPH[nx][ny] > GRAPH[poll.x][poll.y]) {
					sum += DP[nx][ny];
				}
				if (GRAPH[nx][ny] < GRAPH[poll.x][poll.y]) {
					pq.offer(new Pair(nx, ny));
				}
			}
			DP[poll.x][poll.y] = Math.max(DP[poll.x][poll.y], sum);
		}

		System.out.println(DP[N-1][M-1]);
	}

	private static class Pair implements Comparable<Pair>{
		int x, y;
		public Pair(final int x, final int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(final Pair pair) {
			return GRAPH[pair.x][pair.y] - GRAPH[x][y];
		}

		@Override
		public String toString() {
			return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
		}
	}
}
