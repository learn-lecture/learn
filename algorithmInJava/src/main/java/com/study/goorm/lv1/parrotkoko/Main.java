package com.study.goorm.lv1.parrotkoko;

import java.io.*;

public class Main {

    static String vowels = "aeiouAEIOU";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            boolean hasVowel = false;
            for (char c : br.readLine().toCharArray()) {
                if (vowels.contains(String.valueOf(c))) {
                    hasVowel = true;
                    sb.append(c);
                }
            }
            if (!hasVowel) {
                sb.append("???");
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

}