package com.study.datastructrue.string;

import java.util.StringTokenizer;

public class DividePerformTest {

	public static void main(String[] args) {
		String str = "one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five one two three four five";
		//String str = "일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 일 이 삼 사 오 ";

		long startTime = System.nanoTime();
		String[] splitArray = str.split(" ");
		long endTime = System.nanoTime();
		System.out.println("String.split() time: " + (endTime - startTime) + " ns");

		startTime = System.nanoTime();
		StringTokenizer tokenizer = new StringTokenizer(str);
		endTime = System.nanoTime();
		System.out.println("StringTokenizer time: " + (endTime - startTime) + " ns");

		System.out.println("String.split() result:");
		for (String s : splitArray) {
			System.out.print(s);
		}
		System.out.println();

		System.out.println("StringTokenizer result:");
		while (tokenizer.hasMoreTokens()) {
			System.out.print(tokenizer.nextToken());
		}
	}

}
