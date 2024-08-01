package com.study.baekjoon.math.boj27172;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] score = new int[1000001];
	static int[] cards = new int[100001];
	static boolean[] check = new boolean[1000001];
	static int n;

	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			check[cards[i]] = true;
		}

		for (int i = 0; i < n; i++) {
			int card = cards[i];
			for (int commonMultiple = card * 2; commonMultiple <= 1000000; commonMultiple += card) {
				if (check[commonMultiple]) {
					score[commonMultiple]--;
					score[card]++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			sb.append(score[cards[i]]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
