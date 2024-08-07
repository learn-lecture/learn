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
            int prev = 0;
            for (int k = 0; k < seq; k++) {
                int now = Integer.parseInt(st.nextToken());
                for (int check: graph[now]) {
                    if (check == prev) {
                        System.out.println(0);
                        return ;
                    }
                }
                graph[prev].add(now);
                dist[now]++;
                prev = now;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int x: graph[0]) {
            if (--dist[x] == 0) {
                q.add(x);
            }
        }

        boolean[] visited = new boolean[n + 1];
        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            sb.append(now).append('\n');
            for (int next: graph[now]) {
                if (--dist[next] > 0) continue;
                q.add(next);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                sb.append(i).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

}
