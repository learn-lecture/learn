package com.study.algorithm.datastructrue.graph.bfs.boj12851;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder SB = new StringBuilder();
	static int[] DIST = new int[200001];
	static StringTokenizer ST;
	static int N, M;

	static class Pair {
		int x, dis;

		public Pair(final int x, final int dis) {
			this.x = x;
			this.dis = dis;
		}

	}

	public static void main(String[] args) throws IOException {
		ST = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(ST.nextToken());
		M = Integer.parseInt(ST.nextToken());
		Arrays.fill(DIST, Integer.MAX_VALUE);

		Queue<Pair> q = new LinkedList<>();
		DIST[N] = 0;
		q.offer(new Pair(N, 0));
		int dis = 0;

		while (!q.isEmpty()) {
			Pair now = q.poll();
			if (now.x == M) {
				dis = now.dis;
				break;
			}
			int prev = now.x - 1;
			int next = now.x + 1;
			int teleport = now.x * 2;
			int nextDis = now.dis + 1;
			if (prev >= 0 && prev <= 200000 && DIST[prev] >= nextDis) {
				DIST[prev] = nextDis;
				q.offer(new Pair(prev, nextDis));
			}
			if (next >= 0 && next <= 100000 && DIST[next] >= nextDis) {
				DIST[next] = nextDis;
				q.offer(new Pair(next, nextDis));
			}
			if (teleport >= 0 && teleport <= 200000 && DIST[teleport] >= nextDis) {
				DIST[teleport] = nextDis;
				q.offer(new Pair(teleport, nextDis));
			}
		}

		int cnt = 1;
		while (!q.isEmpty()) {
			Pair now = q.poll();
			if (now.x == M && now.dis == dis) cnt++;
		}

		SB.append(dis).append('\n').append(cnt);
		BW.write(SB.toString());
		BW.flush();
	}

}
