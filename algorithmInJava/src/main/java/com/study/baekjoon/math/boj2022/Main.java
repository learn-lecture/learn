package com.study.baekjoon.math.boj2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static double x, y, c;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double left = 0;
        double right = Math.min(x, y);
        double epsilon = 1e-6;
        double d = 0;

        while (right - left > epsilon) {
            double mid = (left + right) / 2;
            double h1 = Math.sqrt(x * x - mid * mid);
            double h2 = Math.sqrt(y * y - mid * mid);
            if ((h1 * h2) / (h1 + h2) >= c) {
                d = mid;
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.printf("%.3f", d);
    }

}
