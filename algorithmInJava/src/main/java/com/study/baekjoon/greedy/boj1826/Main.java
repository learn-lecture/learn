package com.study.baekjoon.greedy.boj1826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int x;
        int oil;
        public Position(int x, int oil) {
            this.x = x;
            this.oil = oil;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Position[] gasStations = new Position[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int oil = Integer.parseInt(st.nextToken());
            gasStations[i] = new Position(x, oil);
        }

        // 정렬되었다는 선 조건이 없으므로 거리 순으로 정렬
        Arrays.sort(gasStations, (a, b) -> a.x - b.x);

        st = new StringTokenizer(br.readLine());
        int destination = Integer.parseInt(st.nextToken());
        int currentOil = Integer.parseInt(st.nextToken());
        int currentPos = 0;
        int stationIndex = 0;
        int stopCount = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // 목적지에 도착할 때까지 반복
        while (currentPos + currentOil < destination) {
            // 이동 할 수 있는 거리 내 충전할 수 있는 oil을 모두 저장
            int canMovingDistance = currentPos + currentOil;
            while (stationIndex < N && gasStations[stationIndex].x <= canMovingDistance) {
                pq.offer(gasStations[stationIndex].oil);
                stationIndex++;
            }

            // 충전할 수 있는 oil이 없다면, 이동할 수 없음.
            if (pq.isEmpty()) {
                stopCount = -1;
                break;
            }

            currentPos += pq.poll();
            stopCount++;
        }

        System.out.println(stopCount);
    }

}

/*
* PM 10:58 ~ 11:53
* 1. 트럭으로 마을까지 이동 중 1KM를 이동 할 때마다 1L의 연료가 빠져 나가는 상황
* 2. 이동하는 곳곳에 N개의 주유소가 존재
* 3. 트럭은 충전 할 때 마다 연료를 충분히 충전할 수 있음.
* 4. 각 각의 주유소 위치와 연료의 양이 주어 질 때, 최소한으로 충전하는 횟수 구하기
* 5. 마을에 도착할 수 없는 경우 -1
*
* 제한사항 1: 주유소 개수 1 <= N <= 10,000
* 제한사항 2: 주유소 위치 1 <= a <= 1,000,000
* 제한사항 3: 주유소 연료 1 <= b <= 100
* 제한사항 4: 현재 위치에서 마을까지 거리 1 <= L <= 1,000,000
* 제한사항 5: 현재 트럭의 연료량 1 <= P <= 1,000,000
*
* 핵심 키포인트: 충분히 많이 충전 -> 한 번에 최대한 많은 이익, 그리디의 핵심
*
* 핵심 포인트: 거리와 연료 중 어느 것을 포커스로 두어야하는가?
* 의심해야 할 포인트 1: 가장 많은 연료를 얻는 것이 좋은가?
* 의심해야 할 포인트 2: 가장 멀리있는 것을 획득하는 것이 좋은가?
*
* 핵심 로직: 한 번에 이동 할 수 있다면, 연료를 굳이 얻을 필요가 없다.
* 현재까지 이동할 수 있는 거리(total oil)로 마을까지 이동할 수 있다면, 더 이상 연료가 필요없다.
* 마을까지 이동할 수 없다면, 현재까지 이동할 수 있는 거리 중 최대의 오일을 선택한다.
* */