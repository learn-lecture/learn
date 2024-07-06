package com.study.datastructrue.linear;

import java.util.Deque;
import java.util.Stack;

public class MyStack {

	public static void main(String[] args) {
		Stack<Integer> st = new Stack<>();
		st.add(10);			st.add(20);			st.add(30);
		st.push(40);		st.push(50);		st.push(60);

		System.out.println(st.pop());
		System.out.println(st.peek());

		for (int i = 0; i < st.size(); i++) {
			System.out.print(st.get(i) + " ");
		}
		System.out.println();

		while (!st.isEmpty()) {
			System.out.print(st.pop() + " ");
		}
	}

}
