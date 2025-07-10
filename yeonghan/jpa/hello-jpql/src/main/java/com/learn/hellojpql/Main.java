package com.learn.hellojpql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] dp = new int[1001][31];
	static int[] plums = new int[1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		// 제자리에 있을 때, 1에서 떨어지는 자두 다 받아먹기
		for (int time = 1; time <= t; time++) {
			int plum = Integer.parseInt(br.readLine());
			plums[time] = plum;
			dp[time][0] = dp[time - 1][0];
			if (plum == 1) {
				dp[time][0]++;
			}
		}
		
		for (int time = 1; time <= t; time++) {
			for (int move = 1; move <= w; move++) {
				// 이전까지 획득한 자두 중 가장 많이 획득한 자두를 저장
				// 이동할 경우 move - 1, 제자리인 경우 move 를 비교
				int accPlums = Math.max(dp[time - 1][move - 1], dp[time - 1][move]);
				dp[time][move] = accPlums;
				// 이동 한 곳이 2고 자두가 2에서 떨어진다면 자두를 먹을 수 있다
				if (move % 2 == 1 && plums[time] == 2) {
					dp[time][move]++;
				}
				// 이동 한 곳이 1이고 자두가 1에서 떨어진다면 자두를 먹을 수 있다.
				if (move % 2 == 0 && plums[time] == 1) {
					dp[time][move]++;
				}
			}
		}

		int maxAccPlums = 0;
		for (int move = 0; move <= w; move++) {
			maxAccPlums = Math.max(dp[t][move], maxAccPlums);
		}
		System.out.println(maxAccPlums);
	}
}