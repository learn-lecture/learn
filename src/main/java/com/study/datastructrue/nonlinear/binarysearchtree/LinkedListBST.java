package com.study.datastructrue.nonlinear.binarysearchtree;

public class LinkedListBST {
	private Node root;
	private int size;

	public LinkedListBST() {
		this.root = null;
		this.size = 0;
	}

	public void add(int value) {
		this.root = addRecursive(root, value);
		this.size++;
	}

	private Node addRecursive(Node current, int value) {
		if (current == null) {
			return new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		}

		return current;
	}

	public boolean delete(int value) {
		int originalSize = size;
		this.root = deleteRecursive(root, value);
		return size < originalSize;
	}

	private Node deleteRecursive(Node current, int value) {
		if (current == null) {
			return null;
		}

		if (value == current.value) {
			if (current.left == null && current.right == null) {
				size--;
				return null;
			}

			if (current.left == null) {
				size--;
				return current.right;
			}

			if (current.right == null) {
				size--;
				return current.left;
			}

			int smallestValue = findSmallestValue(current.right);
			current.value = smallestValue;
			current.right = deleteRecursive(current.right, smallestValue);
			return current;
		}

		if (value < current.value) {
			current.left = deleteRecursive(current.left, value);
			return current;
		}

		current.right = deleteRecursive(current.right, value);
		return current;
	}

	private int findSmallestValue(Node root) {
		return root.left == null ? root.value : findSmallestValue(root.left);
	}

	public void printTree() {
		printTree(root, 0);
	}

	private void printTree(Node node, int level) {
		if (node == null) {
			return;
		}

		printTree(node.right, level + 1);

		if (level != 0) {
			for (int i = 0; i < level - 1; i++) {
				System.out.print("|\t");
			}
			System.out.println("|-------" + node.value);
		} else {
			System.out.println(node.value);
		}

		printTree(node.left, level + 1);
	}

	public void traverseInOrder() {
		traverseInOrder(root);
		System.out.println();
	}

	private void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			System.out.print(" " + node.value);
			traverseInOrder(node.right);
		}
	}

	public void traversePreOrder() {
		traversePreOrder(root);
		System.out.println();
	}
	private void traversePreOrder(Node node) {
		if (node != null) {
			System.out.print(" " + node.value);
			traversePreOrder(node.left);
			traversePreOrder(node.right);
		}
	}
	public void traversePostOrder() {
		traversePostOrder(root);
		System.out.println();
	}
	private void traversePostOrder(Node node) {
		if (node != null) {
			traversePostOrder(node.left);
			traversePostOrder(node.right);
			System.out.print(" " + node.value);
		}
	}

	public int size() {
		return size;
	}

}
