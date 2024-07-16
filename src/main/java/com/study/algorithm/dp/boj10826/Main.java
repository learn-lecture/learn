package com.study.algorithm.dp.boj10826;

import java.io.*;
import java.math.BigInteger;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BigInteger DP[] = new BigInteger[10001];

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(BR.readLine());
		DP[0] = BigInteger.ZERO;
		DP[1] = BigInteger.ONE;
		System.out.println(fibo(n));
	}

	private static BigInteger fibo(int n) {
		if (n <= 1) return DP[n];
		if (DP[n] == null) {
			DP[n] = fibo(n-2).add(fibo(n-1));
		}
		return DP[n];
	}

}
