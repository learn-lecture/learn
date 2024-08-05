package com.study.baekjoon.dp.boj9252;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[][] dp = new int[1001][1001];
	static String str1, str2;

	public static void main(String[] args) throws IOException {
		str1 = br.readLine();
		str2 = br.readLine();

		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}

		int maxLcsLength = dp[str1.length()][str2.length()];
		sb.append(maxLcsLength).append('\n');
		trace(str1.length(), str2.length());

		bw.write(sb.toString());
		bw.flush();
	}

	static void trace(int s1Idx, int s2Idx) {
		if (s1Idx <= 0 || s2Idx <= 0) return;

		if (str1.charAt(s1Idx - 1) == str2.charAt(s2Idx - 1)) {
			trace(s1Idx - 1, s2Idx - 1);
			sb.append(str1.charAt(s1Idx - 1));
		} else {
			if (dp[s1Idx - 1][s2Idx] >= dp[s1Idx][s2Idx - 1]) {
				trace(s1Idx - 1, s2Idx);
			} else {
				trace(s1Idx, s2Idx - 1);
			}
		}
	}

}
