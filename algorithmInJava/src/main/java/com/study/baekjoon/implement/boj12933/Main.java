package com.study.baekjoon.implement.boj12933;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        char[] quacks = br.readLine().toCharArray();
        if (quacks[0] != 'q') {
            System.out.println(-1);
            return ;
        }

        boolean findOtherDuck = false;
        char prev = 'k';
        int duckCount = 1;
        for (char quack: quacks) {
            if (quack == 'q' && prev == 'k') {
                findOtherDuck = false;
                prev = 'q';
            }
            else if (quack == 'u' && prev == 'q') prev = 'u';
            else if (quack == 'a' && prev == 'u') prev = 'a';
            else if (quack == 'c' && prev == 'a') prev = 'c';
            else if (quack == 'k' && prev == 'c') prev = 'k';
            else if (quack == 'q' && !findOtherDuck) {
                findOtherDuck = true;
                duckCount++;
            }
        }

        System.out.println(duckCount);
    }

}