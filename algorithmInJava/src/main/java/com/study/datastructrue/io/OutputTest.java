package com.study.datastructrue.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputTest {

    public static void main(String[] args) throws IOException {
        long bufferStartTime, bufferEndTime;
        long printStartTime, printEndTime;

        int numIterations = 1000;
        String data = "Hello World\n";

        // Using BufferedWriter with larger buffer size
        BufferedWriter bwLarge = new BufferedWriter(new OutputStreamWriter(System.out), 8192 ); // 8 KB buffer
        long largeBufferStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            bwLarge.write(data);
        }
        bwLarge.flush();
        long largeBufferEndTime = System.nanoTime();

        // Using BufferedWriter with default buffer size
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bufferStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            bw.write(data);
        }
        bw.flush();
        bufferEndTime = System.nanoTime();


        // Using System.out.print
        printStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            System.out.print(data);
        }
        printEndTime = System.nanoTime();

        System.out.println("BufferedWriter (default buffer) write time: " + (bufferEndTime - bufferStartTime) + " ns");
        System.out.println("BufferedWriter (large buffer) write time: " + (largeBufferEndTime - largeBufferStartTime) + " ns");
        System.out.println("System.out.print write time: " + (printEndTime - printStartTime) + " ns");
    }
}
