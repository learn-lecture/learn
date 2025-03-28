package com.study.goorm.lv1.bigtriangleofsummer;

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x1 = Double.parseDouble(st.nextToken()); double y1 = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double x2 = Double.parseDouble(st.nextToken()); double y2 = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double x3 = Double.parseDouble(st.nextToken()); double y3 = Double.parseDouble(st.nextToken());

        // d = a + ab * ((ab * ac) / (ab * ab));
        double abx = x2 - x1;	double aby = y2 - y1;
        double acx = x3 - x1;	double acy = y3 - y1;

        double abac = (abx * acx) + (aby * acy);
        double abab = (abx * abx) + (aby * aby);
        double t = abac / abab;

        double footX = x1 + abx * t;
        double footY = y1 + aby * t;

        double height = Math.sqrt((x3 - footX) * (x3 - footX) + (y3 - footY) * (y3 - footY));
        double weight = Math.sqrt(abx * abx + aby * aby);
        System.out.printf("%.2f", (height * weight / 2));
    }

}