package com.study.baekjoon.implement.bruteforce.backtracking.boj2239;
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[][] arr = new int[9][9];
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 9; i++) {
			String info = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = info.charAt(j) - '0';
				if (arr[i][j] == 0) cnt++;
			}
		}

		rec(0);

		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}

	public static boolean rec(int k) {
		if (k == cnt) {
			return true;
		}

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (arr[row][col] > 0) continue;
				for (int num = 1; num < 10; num++) {
					boolean canCol = checkCol(col, num);
					boolean canRow = checkRow(row, num);
					boolean canArea = checkArea(3 * (row / 3), 3 * (col / 3), num);
					if (canCol && canRow && canArea) {
						arr[row][col] = num;
						if (rec(k + 1)) return true;
						arr[row][col] = 0;
					}
				}
				return false;
			}
		}

		return true;
	}

	public static boolean checkArea(int rs, int cs, int key) {
		for (int row = rs; row < rs + 3; row++) {
			for (int col = cs; col < cs + 3; col++) {
				if (arr[row][col] == key) return false;
			}
		}
		return true;
	}

	public static boolean checkCol(int col, int key) {
		for (int row = 0; row < 9; row++) {
			if (arr[row][col] == key) return false;
		}
		return true;
	}

	public static boolean checkRow(int row, int key) {
		for (int col = 0; col < 9; col++) {
			if (arr[row][col] == key) return false;
		}
		return true;
	}

}