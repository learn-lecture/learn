package com.study.baekjoon.dp.fibonacci.boj2748;

import java.io.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static long DP[] = new long[91];

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(BR.readLine());
		DP[1] = 1;
		System.out.println(fibo(n));
	}

	private static long fibo(int n) {
		if (n <= 1) return DP[n];
		if (DP[n] == 0) {
			DP[n] = fibo(n-2) + fibo(n-1);
		}
		return DP[n];
	}

}
