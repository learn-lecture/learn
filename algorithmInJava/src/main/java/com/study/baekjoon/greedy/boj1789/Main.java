package com.study.baekjoon.greedy.boj1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		long s = Long.parseLong(br.readLine());
		int cnt = 0;
		for (long sum = 0, n = 0; sum <= s; n++, sum += n) cnt++;
		System.out.println(cnt - 1);
	}

}
