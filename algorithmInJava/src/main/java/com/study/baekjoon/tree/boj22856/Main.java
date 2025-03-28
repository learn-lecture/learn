package com.study.baekjoon.tree.boj22856;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int RIGHT_NODE_COUNT = 0;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());

		int tree[][] = new int[N+1][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(BR.readLine());
			int node = Integer.parseInt(st.nextToken());
			tree[node][0] = Integer.parseInt(st.nextToken());
			tree[node][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; tree[i][1] != -1; i=tree[i][1]) {
			RIGHT_NODE_COUNT++;
		}

		BW.write(String.valueOf((N - 1) * 2 - RIGHT_NODE_COUNT));
		BW.flush();
		BR.close();
		BW.close();
	}

}
