package com.study.datastructrue.string;

public class StringOutputTest {

	public static void main(String[] args) {
		int iterations = 100;

		long startTime = System.nanoTime();
		String str = "";
		for (int i = 0; i < iterations; i++) {
			str += "a";
		}
		long endTime = System.nanoTime();
		System.out.println("String time: " + (endTime - startTime) + " ns");

		startTime = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < iterations; i++) {
			sb.append("a");
		}
		endTime = System.nanoTime();
		System.out.println("StringBuilder time: " + (endTime - startTime) + " ns");
	}

}
