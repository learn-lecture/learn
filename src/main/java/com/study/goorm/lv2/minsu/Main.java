package com.study.goorm.lv2.minsu;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.print(check(a, b));
    }

    static int check(int a, int b) {
        if (a != b) return 2;
        for (int i = 2; i * i <= b; i++) {
            if (a % i == 0) return i;
        }
        return b;
    }

}