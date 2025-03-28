package com.study.goorm.lv2.aliensmoney;

import java.io.*;
import java.util.*;

class Main {

    static long[] ledger = new long[(int) 1e5 * 2 + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            String info = st.nextToken();
            int money = Integer.parseInt(info.substring(1));

            if (info.charAt(0) == '-') money *= -1;
            ledger[i] = ledger[i-1] + money;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            long total = ledger[e] - ledger[s - 1];
            if (total >= 0) sb.append('+');
            sb.append(total).append('\n');
        }

        System.out.print(sb.toString());
    }

}