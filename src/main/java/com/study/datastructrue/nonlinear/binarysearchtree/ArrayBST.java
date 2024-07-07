package com.study.datastructrue.nonlinear.binarysearchtree;

public class ArrayBST {
	private int[] tree;
	private int size;

	public ArrayBST(int capacity) {
		tree = new int[capacity];
		size = 0;

		for (int i = 0; i < capacity; i++) {
			tree[i] = Integer.MIN_VALUE;
		}
	}

	public void add(int value) {
		if (size == 0) {
			tree[0] = value;
			size++;
		} else {
			addRecursive(0, value);
		}
	}

	private void addRecursive(int index, int value) {
		if (tree[index] == Integer.MIN_VALUE) {
			tree[index] = value;
			size++;
		} else if (value < tree[index]) {
			int leftChildIndex = 2 * index + 1;
			addRecursive(leftChildIndex, value);
		} else if (value > tree[index]) {
			int rightChildIndex = 2 * index + 2;
			addRecursive(rightChildIndex, value);
		}
	}
	public void printTree() {
		printTreeRecursive(0, 0);
	}

	private void printTreeRecursive(int index, int level) {
		if (index >= tree.length || tree[index] == Integer.MIN_VALUE) {
			return;
		}
		printTreeRecursive(2 * index + 2, level + 1);

		for (int i = 0; i < level; i++) {
			System.out.print("    ");
		}
		System.out.println(tree[index]);

		printTreeRecursive(2 * index + 1, level + 1);
	}

}
