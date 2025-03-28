package com.study.baekjoon.set.boj14425;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Set<String> s = new HashSet<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int cnt = 0;

		for (int i = 0; i < n; i++) s.add(br.readLine());
		for (int i = 0; i < m; i++) {
			if (s.contains(br.readLine())) cnt++;
		}

		System.out.println(cnt);
	}

}
