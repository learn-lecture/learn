	package com.study.baekjoon.dp.boj2096;

	import java.io.*;
	import java.util.*;

	public class Main {

		static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		static StringBuffer SB = new StringBuffer();
		static int[][] BASE = new int[100000][3];
		static int[][] DP = new int[100000][3];

		public static void main(String[] args) throws IOException {
			int n = Integer.parseInt(BR.readLine());
			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(BR.readLine());
				for (int j = 0; j < 3; j++) {
					BASE[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 3; i++) DP[0][i] = BASE[0][i];

			for (int i = 1; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = -1; k <= 1; k++) {
						int idx = j + k;
						if (idx < 0 || idx == 3) continue;
						DP[i][j] = Math.max(DP[i][j], BASE[i][j] + DP[i-1][idx]);
					}
				}
			}

			int maxNum = 0;
			for (int i = 0; i < 3; i++) maxNum = Math.max(maxNum, DP[n-1][i]);

			for (int i = 1; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = -1; k <= 1; k++) {
						int idx = j + k;
						if (idx < 0 || idx == 3) continue;
						DP[i][j] = Math.min(DP[i][j], BASE[i][j] + DP[i-1][idx]);
					}
				}
			}

			int minNum = maxNum;
			for (int i = 0; i < 3; i++) minNum = Math.min(minNum, DP[n-1][i]);

			SB.append(maxNum).append(" ").append(minNum);
			BW.write(SB.toString());
			BW.flush();
		}

	}
