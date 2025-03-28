package com.study.baekjoon.greedy.boj30805;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[] arr = new int[101], brr = new int[101];
	static StringTokenizer st;
	static int a, b;

	public static void main(String[] args) throws IOException {
		a = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++) arr[i] = Integer.parseInt(st.nextToken());

		b = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++) brr[i] = Integer.parseInt(st.nextToken());

		Stack<Integer> st = new Stack<>();
		int num = 100;

		int lastAIdx = -1;
		int lastBIdx = -1;
		while (num > 0) {
			boolean isAFind = false;
			boolean isBFind = false;

			int findAidx = 0;
			int findBidx = 0;

			for (int i = lastAIdx + 1; i < a; i++) {
				if (arr[i] == num) {
					findAidx = i;
					isAFind = true;
					break;
				}
			}
			for (int i = lastBIdx + 1; i < b; i++) {
				if(brr[i] == num) {
					findBidx = i;
					isBFind = true;
					break;
				}
			}
			if (isAFind && isBFind) {
				lastAIdx = findAidx;
				lastBIdx = findBidx;
				st.push(num);
			} else num--;
		}

		sb.append(st.size()).append('\n');
		for (int x: st) {
			sb.append(x).append(' ');
		}
		bw.write(sb.toString());
		bw.flush();
	}

}