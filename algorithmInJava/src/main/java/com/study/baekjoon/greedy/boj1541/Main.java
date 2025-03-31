package com.study.baekjoon.greedy.boj1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        String[] positiveNums = s.split("-");
        ArrayList<Integer> nums = new ArrayList<>();
        for (String positiveNum : positiveNums) {
            if (positiveNum.contains("+")) {
                String[] onlyNums = positiveNum.split("\\+");
                int sum = 0;
                for (String onlyNum : onlyNums) {
                    sum += Integer.parseInt(onlyNum);
                }
                nums.add(sum);
            } else {
                nums.add(Integer.parseInt(positiveNum));
            }
        }

        int ans = nums.getFirst();
        for (int i = 1; i < nums.size(); i++) {
            ans -= nums.get(i);
        }
        System.out.println(ans);
    }

}
