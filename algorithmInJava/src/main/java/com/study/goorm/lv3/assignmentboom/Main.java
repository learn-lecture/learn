package com.study.goorm.lv3.assignmentboom;
import java.io.*;
import java.util.*;

class Main {

    static class Pair {
        int start, end;

        Pair (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static List<Pair> assignments = new ArrayList<>();
    static Stack<Pair> st = new Stack();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] startDate = Arrays.stream(st.nextToken().split("/")).mapToInt(Integer::parseInt).toArray();
            int[] endDate = Arrays.stream(st.nextToken().split("/")).mapToInt(Integer::parseInt).toArray();

            int start = startDate[0] * 100 + startDate[1];
            int end = endDate[0] * 100 + endDate[1];
            assignments.add(new Pair(start, end));
        }

        System.out.print(solve(n));
    }

    static String solve(int n) {
        Collections.sort(assignments, (p1, p2) -> {
            if (p1.start == p2.start) {
                return Integer.compare(p2.end, p1.end);
            }
            return Integer.compare(p1.start, p2.start);
        });

        for (Pair assignment: assignments) {
            if (!st.isEmpty() && st.peek().end > assignment.start && st.peek().end < assignment.end) {
                return "No";
            }
            st.push(assignment);
        }
        return "Yes";
    }

}