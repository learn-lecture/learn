package com.study.programmers.kakao2019.openchat;

import java.util.*;

class Pair {
	String uuid;
	boolean check;

	Pair(String uuid, boolean check) {
		this.uuid = uuid;
		this.check = check;
	}
}

class Solution {
	static Map<String, String> LOG = new HashMap<>();
	static StringBuilder SB = new StringBuilder();
	static List<Pair> result = new ArrayList<>();
	static StringTokenizer ST;

	public String[] solution(String[] record) {
		for (String s: record) {
			ST = new StringTokenizer(s);
			String option = ST.nextToken();
			String uuid = ST.nextToken();
			if (option.equals("Leave")) {
				result.add(new Pair(uuid, false));
			} else {
				if (option.equals("Enter")) result.add(new Pair(uuid, true));
				LOG.put(uuid, ST.nextToken());
			}
		}
		return result.stream()
			.map(it -> {
				String name = LOG.get(it.uuid);
				if (it.check) {
					return name + "님이 들어왔습니다.";
				}
				return name + "님이 나갔습니다.";
			}).toArray(String[]::new);
	}
}

public class Main {

	public static void main(String[] args) {
		final String[] input = {
			"Enter uid1234 Muzi",
			"Enter uid4567 Prodo",
			"Leave uid1234",
			"Enter uid1234 Prodo",
			"Change uid4567 Ryan"
		};
		final Solution solution = new Solution();
		String[] output = solution.solution(input);
		for (String out: output) {
			System.out.println(out);
		}
	}

}
