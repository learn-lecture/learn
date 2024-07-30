package com.study.baekjoon.divideandconquer.boj2448;
import java.io.*;
import java.util.*;

public class Main {
	final static char STAR = '*';
	final static char BLANK = ' ';
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	private static void draw(char star[][], int h, int r, int c) {
		if (h == 3) {
			star[r][c] = star[r + 1][c - 1] = star[r + 1][c + 1] = STAR;
			for (int i = 0; i < 5; i++) {
				star[r + 2][c + i - 2] = STAR;
			}
			return ;
		}

		draw(star, h / 2, r, c);
		draw(star, h / 2, r + h / 2, c - h / 2);
		draw(star, h / 2, r + h / 2, c + h / 2);
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		char[][] star = new char[n][2 * n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(star[i], BLANK);
		}

		draw(star, n, 0, n - 1);
		for (int i = 0; i < n; i++) {
			sb.append(String.valueOf(star[i])).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();
	}
}
