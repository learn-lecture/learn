package com.study.datastructrue.nonlinear.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		// 복잡한 트리 설계
		MyTree<Integer> tree = new MyTree<>();

		tree.add(0, 1);
		tree.add(1, 2);
		tree.add(2, 3);
		tree.add(2, 4);
		tree.add(2, 5);

		tree.printTree();

		// PS용 간단한 ADJ 트리 설계
		int vertexCount = 10;
		int level[] = new int[vertexCount + 1];
		ArrayList<ArrayList<Integer>> tree2 = new ArrayList<>();
		ArrayList<StringBuilder> sbs = new ArrayList<>();
		for (int i = 0; i <= vertexCount; i++) {
			sbs.add(new StringBuilder());
			tree2.add(new ArrayList<>());
		}
		tree2.get(1).add(2);
		tree2.get(2).add(3);
		tree2.get(2).add(4);
		tree2.get(2).add(5);
		Queue<Integer> q = new LinkedList<>();
		q.add(1);	level[1] = 1;	sbs.get(1).append(1);
		int maxLevel = 1;
		// BFS
		while (!q.isEmpty()) {
			int x = q.poll();
			maxLevel = Math.max(maxLevel, level[x]);
			for (int i : tree2.get(x)) {
				level[i] = level[x] + 1;
				sbs.get(level[i]).append(i + " ");
				q.add(i);
			}
		}
		for (int i = 1; i <= maxLevel; i++) {
			System.out.println(sbs.get(i).toString());
		}
	}

}
