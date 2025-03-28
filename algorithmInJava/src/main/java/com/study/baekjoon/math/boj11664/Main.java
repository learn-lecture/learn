package com.study.baekjoon.math.boj11664;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static double ax, ay, az, bx, by, bz, cx, cy, cz;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        ax = Double.parseDouble(st.nextToken());    ay = Double.parseDouble(st.nextToken());    az = Double.parseDouble(st.nextToken());
        bx = Double.parseDouble(st.nextToken());    by = Double.parseDouble(st.nextToken());    bz = Double.parseDouble(st.nextToken());
        cx = Double.parseDouble(st.nextToken());    cy = Double.parseDouble(st.nextToken());    cz = Double.parseDouble(st.nextToken());

        double dx = bx - ax;
        double dy = by - ay;
        double dz = bz - az;

        double m = ternarySearch(dx, dy, dz);
        double x0 = ax + m * dx;
        double y0 = ay + m * dy;
        double z0 = az + m * dz;

        System.out.printf("%.10f\n", dist(x0, y0, z0, cx, cy, cz));
    }

    static double ternarySearch(double dx, double dy, double dz) {
        double left = 0.0;
        double right = 1.0;

        while (Math.abs(right-left) >= 1e-7) {
            double m1 = left + (right - left) / 3;
            double m2 = right - (right - left) / 3;
            double m1x = ax + m1 * dx;  double m1y = ay + m1 * dy;  double m1z = az + m1 * dz;
            double m2x = ax + m2 * dx;  double m2y = ay + m2 * dy;  double m2z = az + m2 * dz;

            if (dist(m1x, m1y, m1z, cx, cy, cz) > dist(m2x, m2y, m2z, cx, cy, cz)) {
                left = m1;
            } else {
                right = m2;
            }
        }

        return left;
    }

    static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

}
