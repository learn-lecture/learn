package com.study.baekjoon.graph.dijkstra.boj28707;

import java.io.*;
import java.util.*;

/**
 * acmicpc 28707, 배열 정렬
 * 25.01.08 PM 10:32 ~ 11:34
 */
public class Main {

    public static int n, m;
    public static List<int[]> operations = new ArrayList<>();
    public static Map<String, Integer> costMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] inputArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] sortedArray = inputArray.clone();
        Arrays.sort(sortedArray);
        String sortedState = arrayToString(sortedArray);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            operations.add(new int[]{
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())
            });
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        String startState = arrayToString(inputArray);
        pq.offer(new Node(startState, 0));
        costMap.put(startState, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.state.equals(sortedState)) {
                System.out.println(current.cost);
                return;
            }

            int[] currentArray = stringToArray(current.state);
            for (int[] operation : operations) {
                int[] nextArray = currentArray.clone();
                swap(nextArray, operation[0], operation[1]);

                String nextState = arrayToString(nextArray);
                int nextCost = current.cost + operation[2];

                if (!costMap.containsKey(nextState) || costMap.get(nextState) > nextCost) {
                    costMap.put(nextState, nextCost);
                    pq.offer(new Node(nextState, nextCost));
                }
            }
        }

        System.out.println(-1);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(',');
        }
        return sb.toString();
    }

    static int[] stringToArray(String s) {
        return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    static class Node {
        String state;
        int cost;
        Node(String state, int cost) {
            this.state = state;
            this.cost = cost;
        }
    }

}