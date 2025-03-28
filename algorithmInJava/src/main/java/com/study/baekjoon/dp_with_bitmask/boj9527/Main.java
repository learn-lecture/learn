package com.study.baekjoon.dp_with_bitmask.boj9527;

/*
* acmicpc 9527 - 1의 개수 세기
* 25.01.02 PM 10:55 ~
* */
import java.io.*;
import java.util.*;

public class Main {

    static long[] countByPosition;
    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        countByPosition = new long[55];
        countByPosition[0] = 1;

        for (int i = 1; i < 55; i++) {
            countByPosition[i] = countByPosition[i-1] * 2 + (1L << i);
        }

        System.out.println(countOnesUpTo(B) - countOnesUpTo(A - 1));
    }

    static long countOnesUpTo(long number) {
        long result = number & 1;

        for (int position = 54; position > 0; position--) {
            if ((number & (1L << position)) != 0) {
                result += countByPosition[position - 1] + (number - (1L << position) + 1);
                number -= 1L << position;
            }
        }
        return result;
    }

}