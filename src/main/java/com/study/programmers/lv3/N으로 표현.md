```java
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> countSets = new ArrayList<>();
        countSets.add(new HashSet<>()); 
        
        for (int count = 1; count <= 8; count++) {
            Set<Integer> currentSet = new HashSet<>();
            
            int connected = 0;
            for (int i = 0; i < count; i++) {
                connected = connected * 10 + N;
            }
            currentSet.add(connected);
            
            for (int leftCount = 1; leftCount < count; leftCount++) {
                int rightCount = count - leftCount;
                Set<Integer> leftSet = countSets.get(leftCount);
                Set<Integer> rightSet = countSets.get(rightCount);
                
                for (int leftNum : leftSet) {
                    for (int rightNum : rightSet) {
                        currentSet.add(leftNum + rightNum);
                        currentSet.add(leftNum - rightNum);
                        currentSet.add(leftNum * rightNum);
                        if (rightNum != 0) {
                            currentSet.add(leftNum / rightNum);
                        }
                    }
                }
            }
            
            countSets.add(currentSet);
            
            if (currentSet.contains(number)) {
                return count;
            }
        }
        
        return -1;
    }
}
```

* 해결하지 못함.
* 핵심 로직 
  * N을 n개 사용했을 때 별로, 기록
  * n개 사용 시
    * 1개 사용했을 때 기록과 n-1개 사용했을 때 기록으로 사칙 연산
    * 2개 사용했을 때, ...