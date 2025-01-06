package com.study.baekjoon.twopointer.boj7453;

import java.io.*;
import java.util.*;

/*
* acmicpc 7453, 합이 0인 네 정수
* 25.01.06 PM 09:53 ~
* */
public class Main {
    static int n;
    static int[] AB, CD;
    static int[][] arr = new int[4][4000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 입력
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        AB = new int[n * n];
        CD = new int[n * n];
        int idx = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                AB[idx] = arr[0][i] + arr[1][j];
                CD[idx] = arr[2][i] + arr[3][j];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long answer = 0;
        int left = 0;
        int right = n * n - 1;

        while(left < n * n && right >= 0) {
            long sum = (long)AB[left] + CD[right];
            if(sum == 0) {
                long countLeft = 1;
                long countRight = 1;

                while(left + 1 < n * n && AB[left] == AB[left + 1]) {
                    countLeft++;
                    left++;
                }
                while(right > 0 && CD[right] == CD[right - 1]) {
                    countRight++;
                    right--;
                }

                answer += countLeft * countRight;
                left++;
                right--;
            }
            else if(sum > 0) right--;
            else left++;
        }

        System.out.println(answer);
    }
}