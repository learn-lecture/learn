package com.study.organize;

import java.util.*;

public class LearnCollectionSort {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }

        // Collection + 람다식을 활용해서 정렬 가능
        // 람다식은 메서드로 뺄 수 있음.
        Collections.sort(arr, (o1, o2) -> o2 - o1);
        System.out.println(arr);

        // Collection 자체에서도 제공함
        arr.sort(Comparator.comparingInt(o -> o));
    }

}
