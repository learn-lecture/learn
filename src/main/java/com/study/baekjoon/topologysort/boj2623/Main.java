package com.study.baekjoon.topologysort.boj2623;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] dist = new int[1001];
    static List<Integer>[] graph = new List[1001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int seq = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int k = 1; k < seq; k++) {
                int now = Integer.parseInt(st.nextToken());
                graph[prev].add(now);
                dist[now]++;
                prev = now;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int now = q.poll();
            ans.add(now);
            for (int next: graph[now]) {
                if (--dist[next] > 0) continue;
                q.add(next);
            }
        }

        if (ans.size() == n) {
            for (int x : ans) {
                sb.append(x).append('\n');
            }
        } else {
            sb.append(0);
        }

        bw.write(sb.toString());
        bw.flush();
    }

}