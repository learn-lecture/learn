package com.study.algorithm.divideandconquer.boj11444;

import java.io.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static long[][] FIBO = {{1, 1}, {1, 0}};

	public static void main(String[] args) throws IOException {
		long n = Long.parseLong(BR.readLine());
		// n = 0 FIBO = 1 * FIBO_BASE = 1
		// n = 1 FIBO = {1, 1}, {1, 0} * FIBO_BASE = {1, 1}
		// n = 2 FIBO = {2, 1}, {1, 1} * FIBO_BASE = {2, 1}
		// n = 3 FIBO = {3, 2}, {2, 1} * FIBO_BASE = {3, 2}
		// if n >= 2,
		// result[0][0] == n+1, result[0][1] = n
		// result[1][0] == n, result[1][1] = n-1
		if (n <= 1L) {
			System.out.println(n);
		} else {
			long[][] result = rec(n);
			System.out.println(result[0][1]);
		}
	}

	private static long[][] rec(long exp) {
		if (exp == 1L) return FIBO;

		long result[][] = rec(exp / 2);
		result = times(result, result);

		if ((exp & 1) == 1) {
			result = times(result, FIBO);
		}

		return result;
	}

	private static long[][] times(long[][] op1, long[][] op2) {
		long result[][] = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] += op1[i][k] * op2[k][j];
				}
				result[i][j] %= 1000000007;
			}
		}

		return result;
	}

}
