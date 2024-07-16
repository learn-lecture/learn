package com.study.algorithm.datastructrue.stack.boj9935;

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder SB = new StringBuilder();
	static String NO_CHARACTER = "FRULA";

	public static void main(String[] args) throws IOException {
		String str = BR.readLine();
		String boomStr = BR.readLine();
		int boomLength = boomStr.length();

		Stack<Character> st = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			st.push(str.charAt(i));
			if (st.size() >= boomLength) {
				boolean isEqual = true;
				for (int j = 0; j < boomLength; j++) {
					if (st.get(j + (st.size() - boomLength)) != boomStr.charAt(j)) {
						isEqual = false;
						break;
					}
				}
				if (isEqual) {
					for (int j = 0; j < boomLength; j++) {
						st.pop();
					}
				}
			}
		}

		if (st.empty()) {
			SB.append(NO_CHARACTER);
		} else {
			for (char ch : st) {
				SB.append(ch);
			}
		}

		BW.write(SB.toString());
		BW.close();
		BR.close();
	}

}
