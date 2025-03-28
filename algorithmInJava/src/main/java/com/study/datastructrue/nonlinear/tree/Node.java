package com.study.datastructrue.nonlinear.tree;

import java.util.ArrayList;

public class Node<T> {
	private T data;
	private ArrayList<Node> children;

	public Node(final T data) {
		this.data = data;
		this.children = new ArrayList<>();
	}

	public T getData() {
		return data;
	}

	public void setData(final T data) {
		this.data = data;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void add(Node data) {
		children.add(data);
	}

}
