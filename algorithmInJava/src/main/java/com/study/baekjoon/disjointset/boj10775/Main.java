package com.study.baekjoon.disjointset.boj10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
* acmicpc 10775, 공항
* 25.01.03 PM 11:09 ~ 11:25
* */

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());  // 게이트 수
        int P = Integer.parseInt(br.readLine());  // 비행기 수

        // 부모 배열 초기화
        parent = new int[G + 1];
        for(int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;  // 도킹된 비행기 수

        // 각 비행기에 대해
        for(int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());  // 도킹 가능한 최대 게이트 번호

            int emptyGate = find(gi);  // 도킹 가능한 실제 게이트 찾기

            if(emptyGate == 0) {  // 도킹 불가능
                break;
            }

            union(emptyGate, emptyGate - 1);  // 게이트 도킹 후 왼쪽과 연결
            count++;  // 도킹 성공 카운트 증가
        }

        System.out.println(count);
    }

    // find 연산: 도킹 가능한 게이트 찾기
    static int find(int x) {
        if(x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);  // 경로 압축
    }

    // union 연산: 게이트 연결
    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[x] = y;
        }
    }

}
