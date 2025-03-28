```java
import java.util.*;

class Solution {
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int startIndex = command[0] - 1;
            int lastIndex = command[1];
            int locationIndex = command[2] - 1;
            
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = startIndex; j < lastIndex; j++) {
                temp.add(array[j]);
            }
            temp.sort((a, b) -> a - b);
            
            answer[i] = temp.get(locationIndex);
        }
        
        return answer;
    }
    
}
```