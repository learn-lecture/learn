```java
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        System.out.println(Arrays.toString(citations));

        for (int i = 0; i < n; i++) {
            int h = n - i;  // 현재 위치부터 끝까지의 논문 수
            if (citations[i] >= h) {
                return h;   // h번 이상 인용된 논문이 h편 이상
            }
        }

        return 0;
    }
}
```

* 조금 이해가 덜 됨