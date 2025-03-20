package com.study.organize;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LearnHashSet {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(10);
        boolean contains = set.contains(10);
        System.out.println(contains);
    }

}
