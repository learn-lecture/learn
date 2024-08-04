package com.study.baekjoon.implement.bruteforce.recursion.boj17070;
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static int[][] PIPE = new int[20][20];
	static int HORIZON = 2, VERTICAL = 3, DIAGONAL = 4;
	static StringTokenizer ST;
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(BR.readLine());
		for (int i = 1; i <= N; i++) {
			ST = new StringTokenizer(BR.readLine());
			for (int j = 1; j <= N; j++) {
				PIPE[i][j] = Integer.parseInt(ST.nextToken());
			}
		}
		PIPE[1][1] = PIPE[1][2] = HORIZON;
		System.out.println(rec(1, 2));
	}

	private static int rec(int x, int y) {
		if (x == N && y == N) {
			return 1;
		}
		int cnt = 0;
		int horizon = y + 1;
		int vertical = x + 1;
		int diagonalX = x + 1, diagonalY = y + 1;

		if (diagonalX <= N && diagonalY <= N) {
			if (PIPE[x][horizon] == 0 && PIPE[vertical][y] == 0 && PIPE[diagonalX][diagonalY] == 0) {
				PIPE[diagonalX][diagonalY] = DIAGONAL;
				cnt += rec(diagonalX, diagonalY);
				PIPE[diagonalX][diagonalY] = 0;
			}
		}
		if (horizon <= N && PIPE[x][y] != VERTICAL) {
			if (PIPE[x][horizon] == 0) {
				PIPE[x][horizon] = HORIZON;
				cnt += rec(x, horizon);
				PIPE[x][horizon] = 0;
			}
		}
		if (vertical <= N && PIPE[x][y] != HORIZON) {
			if (PIPE[vertical][y] == 0) {
				PIPE[vertical][y] = VERTICAL;
				cnt += rec(vertical, y);
				PIPE[vertical][y] = 0;
			}
		}
		return cnt;
	}

}
