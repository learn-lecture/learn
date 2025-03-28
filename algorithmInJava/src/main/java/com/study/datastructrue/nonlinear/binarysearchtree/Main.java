package com.study.datastructrue.nonlinear.binarysearchtree;

import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		LinkedListBST bt = new LinkedListBST();

		bt.add(6);
		bt.add(4);
		bt.add(8);
		bt.add(3);
		bt.add(5);
		bt.add(7);
		bt.add(9);

		bt.printTree();
		bt.traversePreOrder();
		bt.traverseInOrder();
		bt.traversePostOrder();

		bt.delete(4);
		bt.printTree();
		bt.traverseInOrder();

		ArrayBST bst = new ArrayBST(10);
		bst.add(6);
		bst.add(4);
		bst.add(8);
		bst.add(3);
		bst.add(5);
		bst.add(7);
		bst.add(9);
		bst.printTree();
	}

}
