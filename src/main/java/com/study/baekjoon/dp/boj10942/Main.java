package com.study.baekjoon.dp.boj10942;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static boolean[][] dp = new boolean[2001][2001];
	static int[] nums = new int[2001];
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = true;

			int prev = i - 1;
			if (nums[prev] == nums[i]) {
				dp[prev][i] = true;
			}
		}

		for (int i = n - 1; i >= 1; i--) {
			for (int j = i + 2; j <= n; j++) {
				if (nums[i] != nums[j]) continue;
				if (dp[i + 1][j - 1]) dp[i][j] = true;
			}
		}

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(dp[s][e] ? "1" : "0").append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
