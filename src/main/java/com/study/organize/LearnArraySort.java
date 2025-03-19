package com.study.organize;

import java.util.Arrays;
import java.util.Comparator;

public class LearnArraySort {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        // 1차원 배열 내림차순 정렬
        // array 는 기본적으로 오름차순을 지원함.
        // 해결하려면 Wrapper로 Boxing을 해줘야 함.
        Integer[] arrObj = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(arrObj, (a, b) -> b - a);   // Wrapper는 람다식으로 됨
        arr = Arrays.stream(arrObj).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(arr));

        // 1차원 배열 오름차순 정렬
        // 전체 정렬 시 index 정보 넣지 않아도 됨.
        Arrays.sort(arr, 0, 10);
        System.out.println(Arrays.toString(arr));

        int[][] arr2 = new int[10][2];
        for (int i = 0; i < 10; i++) {
            arr2[i][0] = i;
            arr2[i][1] = 10;
        }
        System.out.println(Arrays.deepToString(arr2));

        // 2차원 배열 첫 번째 요소를 기준으로 내림차순
        Arrays.sort(arr2, (a, b) -> b[0] - a[0]);
        System.out.println(Arrays.deepToString(arr2));

        // 2차원 배열 첫 번째 요소를 기준으로 오름차순
        // Comparator.comparingInt method를 활용할 수도 있지만, 람다가 더 편할수도
        Arrays.sort(arr2, Comparator.comparingInt(a -> a[0]));
        System.out.println(Arrays.deepToString(arr2));

        // Class Sorting
        Test[] tests = new Test[10];
        for (int i = 0; i < 10; i++) {
            tests[i] = new Test(i, i + 10);
        }
        Arrays.sort(tests, (a, b) -> b.a - a.a);
        System.out.println(Arrays.toString(tests));

        Arrays.sort(tests, Comparator.comparingInt(t -> t.a));
        System.out.println(Arrays.toString(tests));

        // string
        String[] strings = new String[10];
        for (int i = 0; i < 10; i++) {
            strings[i] = String.valueOf(i);
        }
        // Comparator.reverseOrder() 로 됨. 기본 사전순
        Arrays.sort(strings, (s1, s2) -> s2.compareTo(s1));
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
    }

    static class Test {
        int a;
        int b;
        Test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "a: " + a + ", b: " + b;
        }
    }

}
