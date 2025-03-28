package com.study.datastructrue.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputTest {

    public static void main(String[] args) throws IOException {
        long startTime, endTime;
        String input = "";
        for (int i = 0; i < 100000; i++) {
            input += "AAAAAAAAAAAAAAAAAAAA";
        }
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // fread
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        startTime = System.nanoTime();
        String line = br.readLine();
        endTime = System.nanoTime();
        System.out.println("BufferedReader read time: " + (endTime - startTime) + " ns");


        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // read
        Scanner sc = new Scanner(System.in);
        startTime = System.nanoTime();
        line = sc.nextLine();
        endTime = System.nanoTime();
        System.out.println("Scanner read time: " + (endTime - startTime) + " ns");
    }

}
