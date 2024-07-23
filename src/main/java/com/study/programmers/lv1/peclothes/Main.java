package com.study.programmers.lv1.peclothes;

class Solution {

    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n+2];
        int answer = 0;

        for (int stu: lost) students[stu]--;
        for (int stu: reserve) students[stu]++;

        for (int i = 1; i <= n; i++) {
            int prev = i - 1;
            int next = i + 1;
            if (students[i] < 0) {
                if (students[prev] > 0) {
                    students[prev]--;
                    students[i]++;
                } else if (students[next] > 0) {
                    students[next]--;
                    students[i]++;
                }
            }
            if (students[i] >= 0) answer++;
        }

        return answer;
    }

}

public class Main {

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        Solution solution = new Solution();
        System.out.println(solution.solution(n, lost, reserve));
    }

}
