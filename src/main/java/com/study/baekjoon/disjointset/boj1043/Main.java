package com.study.baekjoon.disjointset.boj1043;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static int[] PARENT = new int[51];
	static List<Integer>[] PARTY = new List[51];
	static int N, M, KNOW_PEOPLE_NUMBER;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(BR.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			PARENT[i] = i;
		}

		for (int i = 0; i < M; i++) {
			PARTY[i] = new ArrayList<>();
		}

		st = new StringTokenizer(BR.readLine());
		KNOW_PEOPLE_NUMBER = Integer.parseInt(st.nextToken());
		for (int i = 0; i < KNOW_PEOPLE_NUMBER; i++) {
			PARENT[Integer.parseInt(st.nextToken())] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(BR.readLine());
			int invitedCount = Integer.parseInt(st.nextToken());
			int firstPerson = Integer.parseInt(st.nextToken());
			int firstParent = find(firstPerson);
			PARTY[i].add(firstPerson);

			for (int j = 1; j < invitedCount; j++) {
				int nextPerson = Integer.parseInt(st.nextToken());
				int nextParent = find(nextPerson);
				PARTY[i].add(nextPerson);
				union(firstParent, nextParent);
			}
		}

		int can = M;
		for (int i = 0; i < M; i++) {
			for (int person: PARTY[i]) {
				if (find(person) == 0) {
					can--;
					break;
				}
			}
		}

		System.out.println(can);
	}

	private static int find(int x) {
		if (x != PARENT[x]) {
			int parent = find(PARENT[x]);
			PARENT[x] = parent;
		}
		return PARENT[x];
	}

	private static void union(int x, int y) {
		if (PARENT[x] < PARENT[y]) {
			PARENT[y] = PARENT[x];
		} else {
			PARENT[x] = PARENT[y];
		}
	}

}
