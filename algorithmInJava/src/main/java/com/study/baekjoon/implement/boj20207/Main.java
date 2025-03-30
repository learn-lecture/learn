package com.study.baekjoon.implement.boj20207;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] count = new int[366];
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int firstDay = Integer.parseInt(st.nextToken());
            int lastDay = Integer.parseInt(st.nextToken());

            // 시작 일자부터 종료일자까지 일정 수 카운트
            for (int day = firstDay; day <= lastDay; day++) {
                count[day]++;
            }
        }

        int totalArea = 0;
        int width = 0;
        int height = 0;

        // 1일부터 365일까지 스캔하며 연속된 일정 그룹 찾기
        for (int day = 1; day <= 365; day++) {
            if (count[day] > 0) {
                // 현재 날짜에 일정이 있으면 폭 증가, 높이 업데이트
                width++;
                height = Math.max(height, count[day]);
            } else {
                // 현재 날짜에 일정이 없으면 이전 그룹의 면적 계산 후 초기화
                totalArea += width * height;
                width = 0;
                height = 0;
            }
        }

        totalArea += width * height;
        System.out.println(totalArea);
    }

}

/*
* PM 10:02 ~ 10:38
* 1. 날짜가 1일 ~ 365일로 표시되어 있는 달력을 가지고 있음.
* 2. 올해 일정을 모두 계획해서 달력에 표시함.
* 3. 날씨로 인해 달력에 표시한 일정 중 일부가 지워지려고 함.
* 4. 방지하기 위해 일정이 있는 곳에만 코팅지를 달력에 붙이려고 함.
* 5. 너무 귀찮은 탓에 아래와 같은 규칙을 따라 코팅지를 붙이려고 한다.
* 5-1. 연속된 일정을 모두 감쌀 수 있는 가장 작은 직사각형의 코팅지를 만들어 붙인다.
* 5-2. 연속된 두 일자에 각 각 일정이 1개 이상있다면, 연속된 일정이다.
* 5-3. 연속된 모든 일정은 하나의 직사각형에 포함되어야 한다.
* 6. 달력은 아래와 같은 규칙을 따른다.
* 6-1. 일정은 시작 날짜와 종료 날짜를 포함한다.
* 6-2. 시작일이 가장 앞선 일정부터 차례대로 채워진다.
* 6-3. 시작일이 같은 경우 일정의 기간이 긴 것부터 채워진다.
* 6-4. 일정은 가능한 한 최 상단에 배치된다.
* 6-5. 일정 하나의 세로의 길이는 1이다.
* 6-6. 하루의 폭은 1이다.
*
* 핵심 1. 연속된 일정을 순서대로 처리해야한다. - 그리디
* 핵심 2. 연속된 일정 중 하루에 가장 많은 일정의 수를 찾는다.
* 핵심 3. 연속된 일정의 길이 * 일정 중 가장 많은 일정의 수로 끝나는 일정 별로 코팅지 면적을 구한다.
*
* 어려운 포인트. 하루에 가장 많은 일정의 수를 어떻게 구할 것인가?
* N <= 1000, day <= 365
* 모든 일정 별로 몇개의 일정이 있는지 count한다. -> 3650000번 < 1초
* */
