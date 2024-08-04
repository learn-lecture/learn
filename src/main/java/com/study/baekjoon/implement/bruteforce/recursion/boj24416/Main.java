package com.study.baekjoon.implement.bruteforce.recursion.boj24416;
import java.io.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(BR.readLine());
		System.out.println(fibo(n) + " " + (n-2));
	}

	private static int fibo(int n) {
		if (n == 1 || n == 2) return 1;
		int cnt = fibo(n-1) + fibo(n-2);
		return cnt;
	}

}
