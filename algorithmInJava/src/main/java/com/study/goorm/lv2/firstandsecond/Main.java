package com.study.goorm.lv2.firstandsecond;

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Character> st = new Stack();
        boolean oneTwo = false;
        boolean twoOne = false;
        for (int i = 0; i < input.length(); i++) {
            if (!st.isEmpty() && st.peek() == '2' && input.charAt(i) == '1' && !oneTwo) {
                oneTwo = true;
            } else if (!st.isEmpty() && st.peek() == '1' && input.charAt(i) == '2' && !twoOne) {
                twoOne = true;
            } else {
                st.push(input.charAt(i));
            }
        }

        if (oneTwo && twoOne) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
}