package com.study.datastructrue.nonlinear.tree;

import java.util.ArrayList;

public class MyTree<T> {

	private Node<T> root;

	public MyTree() {
		this.root = null;
	}

	public void add(T to, T data) {
		if (this.root == null) {
			this.root = new Node(data);
			return ;
		}
		final Node node = findFirst(to);
		if (node == null) {
			throw new IllegalArgumentException("NOT Found Node");
		}
		node.add(new Node(data));
	}

	public Node findFirst(T data) {
		if (root.getData() == data) return root;
		return findFirst(root, data);
	}

	private Node findFirst(Node<T> cur, T data) {
		for (int i = 0; i < cur.getChildren().size(); i++) {
			if (cur.getChildren().get(i).getData() == data) {
				return cur.getChildren().get(i);
			}
		}
		for (Node node : root.getChildren()) {
			return findFirst(node, data);
		}
		return null;
	}

	public void printTree() {
		ArrayList<StringBuilder> sbs = new ArrayList<>();
		final int depth = depth(root, 0);
		for (int i = 0; i <= depth; i++) {
			sbs.add(new StringBuilder());
		}
		printTree(root, 0, sbs);
		for (StringBuilder sb: sbs) {
			System.out.println(sb.toString());
		}
	}

	private int depth(Node<T> cur, int level) {
		int maxLevel = level;
		for (int i = 0; i < cur.getChildren().size(); i++) {
			maxLevel = Math.max(depth(cur.getChildren().get(i), level + 1), maxLevel);
		}
		return maxLevel;
	}

	private void printTree(Node<T> cur, int level, final ArrayList<StringBuilder> sbs) {
		StringBuilder sb = sbs.get(level);
		sb.append(cur.getData() + " ");
		for (int i = 0; i < cur.getChildren().size(); i++) {
			printTree(cur.getChildren().get(i), level + 1, sbs);
		}
		sb.append("|");

	}


}
