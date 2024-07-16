package com.study.algorithm.bruteforce.recursion.boj10870;
import java.io.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(BR.readLine());
		System.out.println(fibo(n));
	}

	private static int fibo(int n) {
		if (n <= 1) return n;
		return fibo(n-2) + fibo(n-1);
	}

}