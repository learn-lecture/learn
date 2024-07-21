package com.study.baekjoon.bruteforce.backtracking.boj16953;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static Set<Long> MEM = new HashSet<>();
	static int A, B;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		int result = solve(A, B, 1);
		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		BW.write(String.valueOf(result));
		BW.flush();
		BW.close();
		BR.close();
	}

	private static int solve(long a, int b, int k) {
		if (a == b) return k;

		int cnt = Integer.MAX_VALUE;
		if (a > b) return cnt;
		if (MEM.contains(a)) return cnt;

		MEM.add(a);
		cnt = Math.min(solve(a * 2, b, k+1), cnt);
		cnt = Math.min(solve(a * 10 + 1, b, k+1), cnt);

		return cnt;
	}

}
