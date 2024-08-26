package com.study.goorm.lv2.makea4;

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] paper = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long row = (paper[0] / 20) * (paper[1] / 40);
        long col = (paper[0] / 40) * (paper[1] / 20);
        long rowcol = (paper[0] / 40) * (paper[1] / 40);
        System.out.print((row + col - 2 * rowcol));
    }

}