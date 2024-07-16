package com.study.algorithm.dp.boj12865;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] DP = new int[100001][101];
	static int[] W = new int[101], V = new int[101];
	static int N, K;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(BR.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				DP[i][j] = DP[i][j-1];
				if (i - W[j] < 0) continue;
				DP[i][j] = Math.max(DP[i][j], DP[i-W[j]][j-1] + V[j]);
			}
		}

		BW.write(String.valueOf(DP[K][N]));
		BW.flush();
		BW.close();
		BR.close();
	}

}
